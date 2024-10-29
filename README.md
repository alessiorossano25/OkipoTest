# Okipotest Application

## Descrizione

Questa applicazione è un servizio RESTful sviluppato con Spring Boot che interagisce con l'API di Etherscan per :
- Verificare l'esistenza e il bilancio di un indirizzo Ethereum.
- Salvare le transazioni dell'indirizzo e visualizzarle tramite paginazione.


## Struttura

L'applicazione è composta da:
- **ControllerLayer**: gestione delle richieste HTTP.
- **Model**: contiene i modelli che rappresentano le tabelle del DB e quelli utilizzati per la gestione dei dati.
- **Repository**: interfacce per l'accesso e la gestione del database.
- **Response**: modelli che rappresentano la struttura delle risposte delle chiamate API.
- **ServiceLayer**: la logica principale per le chiamate all'API e la gestione dei dati.
- **Config**: configurazione di Web Client utile per effettuare chiamate API verso l'esterno.
- **Utilities**: classe utilizzata per creare i metodi statici che possono essere utilizzati in altre parti del programma.


### Endpoint

1. **GET /api/checkAddress/{address}**
   - **Descrizione**: Recupera e aggiorna le informazioni relative a un indirizzo Ethereum.
   - **Parametri**:
     - `address`: indirizzo Ethereum.
   - **Risposte**:
     - `200 OK`: se l'operazione è andata a buon fine.
     - `400 Bad Request`: se l'indirizzo non è valido o se si verifica un errore.

2. **GET /api/checkTransaction/{address}/{page}**
   - **Descrizione**: Recupera il balance e la lista delle transazioni associate a un indirizzo Ethereum con paginazione.
   - **Parametri**:
     - `address`: indirizzo Ethereum.
     - `page`: numero della pagina delle transazioni da visualizzare (inizia da 1).
   - **Risposte**:
     - `200 OK`: se le transazioni vengono recuperate con successo.
     - `400 Bad Request`: se l'indirizzo non è presente nel database.





