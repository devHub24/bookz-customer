package com.sk.bookz_customer.mapper;


import com.sk.bookz_customer.dto.CustomerDto;
import com.sk.bookz_customer.entity.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerMapper {

    public static CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
//        customerDto.setId(customer.getId());
//        customerDto.setName(customer.getName());
//        customerDto.setEmail(customer.getEmail());
//        customerDto.setNumber( customer.getNumber());
//        customerDto.setPassword(customer.getPassword());
//        customerDto.setNumber(customer.getNumber());
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    public static Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }
}
