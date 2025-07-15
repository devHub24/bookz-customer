package com.sk.bookz_customer.controller;

import com.sk.bookz_customer.dto.CustomerDto;
import com.sk.bookz_customer.entity.Customer;
import com.sk.bookz_customer.service.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.newCustomer(customerDto));
    }
}
