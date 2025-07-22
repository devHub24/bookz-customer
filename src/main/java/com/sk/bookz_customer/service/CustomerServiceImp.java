package com.sk.bookz_customer.service;

import com.sk.bookz_customer.constants.CustomerStatus;
import com.sk.bookz_customer.dto.CustomerDto;
import com.sk.bookz_customer.entity.Customer;
import com.sk.bookz_customer.exception.CustomerAlreadyExistsException;
import com.sk.bookz_customer.exception.CustomerNotFoundException;
import com.sk.bookz_customer.mapper.CustomerMapper;
import com.sk.bookz_customer.repo.ICustomerRepo;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements ICustomerService {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImp.class);

    private final ICustomerRepo customerRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Timer customerTimer;
    @Autowired
    private DistributionSummary distributionSummary;

    public CustomerServiceImp(ICustomerRepo customerRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepo = customerRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @SneakyThrows
    public CustomerDto newCustomer(CustomerDto customerDto) {
        customerDto.setPassword(encryptPassword(customerDto.getPassword()));
        if(customerDto.getCustomerStatus()==null) customerDto.setCustomerStatus(CustomerStatus.ACTIVE);
        try{
            Customer savedCustomer = customerRepo.save(CustomerMapper.toCustomer(customerDto));
            LOGGER.info("New customer saved successfully:{}", savedCustomer);
            return CustomerMapper.toCustomerDto(savedCustomer);
        }catch(DataIntegrityViolationException ex){
            throw new CustomerAlreadyExistsException("Customer Already Exists with mail id: "+customerDto.getEmail());
        }
    }

    private String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public  Long count(){
        return customerRepo.count();
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerRepo.findAll()
                .stream()
                .map(CustomerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepo.findById(id).orElseThrow(
                ()->new CustomerNotFoundException("Customer not found with id:"+id));
        return CustomerMapper.toCustomerDto(customerRepo.save(
                CustomerMapper.updateCustomerMapper(customerDto, customer)));
    }


}
