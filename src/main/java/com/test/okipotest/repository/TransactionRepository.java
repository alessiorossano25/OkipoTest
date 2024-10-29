package com.test.okipotest.repository;

import com.test.okipotest.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByAddress_AddressOrderByTimeStampDesc(String address, Pageable pageable);
}
