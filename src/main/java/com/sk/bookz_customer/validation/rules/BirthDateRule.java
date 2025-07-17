package com.sk.bookz_customer.validation.rules;

import java.time.LocalDate;

public class BirthDateRule implements DateRule{


    @Override
    public boolean validate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    @Override
    public String getErrorMessage() {
        return "Invalid BirthDate";
    }
}
