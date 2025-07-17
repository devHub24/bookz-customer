package com.sk.bookz_customer.validation.rules;

public interface PhoneRule extends CustomerValidationRule{
    boolean validate(String phone);
}
