package com.sk.bookz_customer.controller;

import com.sk.bookz_customer.annotations.markers.OnCreate;
import com.sk.bookz_customer.annotations.markers.OnUpdate;
import com.sk.bookz_customer.dto.CustomerDto;
import com.sk.bookz_customer.entity.Customer;
import com.sk.bookz_customer.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody @Validated(OnCreate.class) CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.newCustomer(customerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") long id,
                                                      @RequestBody @Validated(OnUpdate.class)  CustomerDto customerDto){
        CustomerDto result = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") long id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(customerService.findByEmail(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable("id") long id){
        customerService.delete(id);
    }

}
