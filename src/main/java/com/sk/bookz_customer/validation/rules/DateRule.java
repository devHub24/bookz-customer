package com.sk.bookz_customer.validation.rules;

import java.time.LocalDate;

public interface DateRule extends CustomerValidationRule {
    public boolean validate(LocalDate date);
}
