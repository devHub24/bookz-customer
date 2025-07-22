package com.sk.bookz_customer.repo;

import com.sk.bookz_customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {

    public Optional<Customer> findByEmail(String email);
}
