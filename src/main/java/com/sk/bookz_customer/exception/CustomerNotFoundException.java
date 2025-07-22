package com.sk.bookz_customer.exception;

import lombok.Getter;

@Getter
public class CustomerNotFoundException extends RuntimeException {

    private String message;

    public CustomerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
