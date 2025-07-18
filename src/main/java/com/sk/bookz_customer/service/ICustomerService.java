package com.sk.bookz_customer.service;

import com.sk.bookz_customer.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {

    public CustomerDto newCustomer(CustomerDto customerDto);
    public Long count();
    public List<CustomerDto> findAll();
}
