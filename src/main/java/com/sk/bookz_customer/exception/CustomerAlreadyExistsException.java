package com.sk.bookz_customer.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    private String message;

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
