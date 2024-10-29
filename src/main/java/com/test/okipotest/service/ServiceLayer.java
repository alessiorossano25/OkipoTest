package com.test.okipotest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.okipotest.Utilities;
import com.test.okipotest.model.APIAddressResponse;
import com.test.okipotest.model.APITransactionResponse;
import com.test.okipotest.model.Address;
import com.test.okipotest.model.Transaction;
import com.test.okipotest.repository.AddressRepository;
import com.test.okipotest.repository.TransactionRepository;
import com.test.okipotest.response.TransactionResponse;
import com.test.okipotest.response.TransactionResponseBonus;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceLayer {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WebClient webClient;
    @Value("${api.key}")
    private String apikey;
    //variabile che permette di convertire una stringa in oggetto
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(ServiceLayer.class);

    @Transactional
    public String checkAddress(String addr) {
        //se l'indirizzo esiste nel database
        APITransactionResponse apiTransactionResponse = null;
        APIAddressResponse apiAddressResponse = null;

        double balance = 0;
        try {

            String url = "https://api.etherscan.io/api?module=account&action=balance&address=" + addr + "&tag=latest&apikey=" + apikey;
            //chiamata API a Etherscan per il balance
            String response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            apiAddressResponse = objectMapper.readValue(response, APIAddressResponse.class);

            if (apiAddressResponse.getMessage().equals("NOTOK")) {
                logger.error("Indirizzo non validato");
                return "indirizzo non valido";
            }

            // Converto la stringa in un BigInteger in quanto ci sono 18 decimali nel balance
            BigInteger balanceInWei = new BigInteger(apiAddressResponse.getResult());
            // Dividp per 10 elevato 18 per ottenere il balance in Ethereum
            BigDecimal balanceInEther = new BigDecimal(balanceInWei).divide(new BigDecimal("1000000000000000000"));
            balance = balanceInEther.doubleValue();

            url = "https://api.etherscan.io/api?module=account&action=txlist&address=" + addr + "&startblock=0&endblock=99999999&sort=asc&apikey=" + apikey;
            //chiamata API a Etherscan
            response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            //conversione da Stringa a Oggetto mediante objectMapper
            apiTransactionResponse = objectMapper.readValue(response, APITransactionResponse.class);

            if (apiTransactionResponse.getMessage().equals("NOTOK")) {
                logger.error("Indirizzo non validato");
                return "indirizzo non valido";
            }

        } catch (Exception e) {
            logger.error("Errore nella chiamata Etherscan", e);
            return "API non disponibile";
        }

        if (addressRepository.existsByAddress(addr)) {
            Address address = addressRepository.findByAddress(addr);

            List<Transaction> TransactionUpdate = apiTransactionResponse.getResult().stream()
                    /* verifico la data dell'ultimo update convertendo LocalDateTime in timestamp
                    (con metodo custom statico presente nella classe Utilities) e che deve essere minore del timestamp della transazione */
                    .filter(result -> Long.parseLong(result.getTimeStamp()) > Utilities.convertLocalDateTimeToTimeStamp(address.getLastUpdateAt()))
                    //per rendere il codice più pulito, ho creato un costruttore custom nella classe Transaction
                    .map(result -> new Transaction(result, address)).collect(Collectors.toList());
            //salvo tutto su DB
            transactionRepository.saveAll(TransactionUpdate);
            address.setBalance(balance);
            address.setLastUpdateAt(LocalDateTime.now());
            addressRepository.save(address);
            logger.info("indirizzo esistente, salvate nuove transazioni");
        } else {
            //creato costruttore custom per creare il nuovo indirizzo
            Address address = new Address(addr,balance);
            // creo la variabile saveAddress perchè contiene anche l'id che servirà a Transaction per la chiave esterna
            Address saveAddress = addressRepository.save(address);
            List<Transaction> newTransaction = apiTransactionResponse.getResult().stream()
                    .map(result -> new Transaction(result, saveAddress))
                    .collect(Collectors.toList());
            //salvo tutto su DB
            transactionRepository.saveAll(newTransaction);
            logger.info("indirizzo non esistente, salvate transazioni");

        }
        return "operazione andata a buon fine";
    }

    @Transactional
    public TransactionResponseBonus checkTransaction(String addr, int page) {
        if (!addressRepository.existsByAddress(addr)) {
            logger.error("indirizzo non presente nel DB");
            return new TransactionResponseBonus(0,new ArrayList<>());
        }
        Address address = addressRepository.findByAddress(addr);
        Pageable pageable = PageRequest.of(page,10);
        List<Transaction> AllTransaction = transactionRepository.findByAddress_AddressOrderByTimeStampDesc(addr,pageable);
        //costruttore custom per ottenere la lista di TransactionResponse che contiene solo i campi richiesti
        List<TransactionResponse> transactionResponses = AllTransaction.stream()
                .map(transaction -> new TransactionResponse(transaction))
                .collect(Collectors.toList());
        logger.info("Lista transazioni per indirizzo " + addr);
        return new TransactionResponseBonus(address.getBalance(),transactionResponses);
    }

}
