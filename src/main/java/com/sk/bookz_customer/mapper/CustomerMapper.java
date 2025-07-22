package com.sk.bookz_customer.mapper;


import com.sk.bookz_customer.dto.CustomerDto;
import com.sk.bookz_customer.entity.Customer;
import com.sk.bookz_customer.service.CustomerServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

public class CustomerMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerMapper.class);
    public static CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
//        customerDto.setId(customer.getId());
//        customerDto.setName(customer.getName());
//        customerDto.setEmail(customer.getEmail());
//        customerDto.setNumber( customer.getNumber());
//        customerDto.setPassword(customer.getPassword());
//        customerDto.setNumber(customer.getNumber());
        BeanUtils.copyProperties(customer, customerDto);
        LOGGER.info("Converted customerDto: {}", customerDto);
        return customerDto;
    }

    public static Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        LOGGER.info("Converted customerDto: {}", customer);
        return customer;
    }

    public static Customer updateCustomerMapper(CustomerDto customerDto, Customer customer) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(customerDto);
            String [] nullFields = Arrays.stream(beanWrapper.getPropertyDescriptors())
                    .map(PropertyDescriptor::getName)
                    .filter(name -> beanWrapper.getPropertyValue(name) == null || beanWrapper.getPropertyValue(name).toString().equals(""))
                    .toArray(String[]::new);
            BeanUtils.copyProperties(customerDto, customer, nullFields);
        LOGGER.info("Converted Customer: {}", customer);
            return customer;
    }
}
