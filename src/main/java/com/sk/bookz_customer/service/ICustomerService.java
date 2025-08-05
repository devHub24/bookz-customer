package com.sk.bookz_customer.service;

import com.sk.bookz_customer.dto.CustomerDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ICustomerService extends UserDetailsService {

    public CustomerDto newCustomer(CustomerDto customerDto);
    public Long count();
    public List<CustomerDto> findAll();
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    public CustomerDto findById(Long id);
    public CustomerDto findByEmail(String email);
    public void delete(Long id);

}
