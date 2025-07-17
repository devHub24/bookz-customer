package com.sk.bookz_customer.validation.rules;

public interface PasswordRule extends CustomerValidationRule {

    boolean validate(String password);
}
