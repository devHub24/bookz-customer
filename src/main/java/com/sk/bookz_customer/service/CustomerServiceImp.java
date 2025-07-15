package com.sk.bookz_customer.service;

import com.sk.bookz_customer.constants.CustomerStatus;
import com.sk.bookz_customer.dto.CustomerDto;
import com.sk.bookz_customer.entity.Customer;
import com.sk.bookz_customer.mapper.CustomerMapper;
import com.sk.bookz_customer.repo.ICustomerRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements ICustomerService {

    private final ICustomerRepo customerRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImp(ICustomerRepo customerRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepo = customerRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public CustomerDto newCustomer(CustomerDto customerDto) {
        customerDto.setPassword(encryptPassword(customerDto.getPassword()));
        customerDto.setCustomerStatus(CustomerStatus.ACTIVE);
        Customer savedCustomer = customerRepo.save(CustomerMapper.toCustomer(customerDto));
        return CustomerMapper.toCustomerDto(savedCustomer);
    }

    private String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
