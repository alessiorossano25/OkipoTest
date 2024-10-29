package com.test.okipotest.repository;

import com.test.okipotest.model.Address;
import com.test.okipotest.model.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsByAddress(String address);

    Address findByAddress(String address);
}
