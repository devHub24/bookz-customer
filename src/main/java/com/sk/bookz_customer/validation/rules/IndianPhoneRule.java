package com.sk.bookz_customer.validation.rules;

import lombok.*;


@Getter
@Setter
public class IndianPhoneRule implements PhoneRule{

    public IndianPhoneRule() {}

    @Override
    public boolean validate(String phone) {
        return phone.length()==10;
    }

    @Override
    public String getErrorMessage() {
        return "Phone number is invalid indian phone number";
    }
}
