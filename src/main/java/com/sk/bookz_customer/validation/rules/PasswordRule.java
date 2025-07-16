package com.sk.bookz_customer.validation.rules;

public interface PasswordRule {

    boolean validate(String password);
    String getErrorMessage();
}
