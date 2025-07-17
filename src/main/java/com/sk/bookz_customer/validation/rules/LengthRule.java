package com.sk.bookz_customer.validation.rules;

import lombok.Data;

@Data
public class LengthRule implements PasswordRule {

    private int minLength;
    private int maxLength;

    public LengthRule(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
    public LengthRule() {

    }

    @Override
    public boolean validate(String password) {
        return password.length() >= minLength && password.length() <= maxLength;
    }

    @Override
    public String getErrorMessage() {
        return "Length Should be between " + minLength + " and " + maxLength;
    }
}
