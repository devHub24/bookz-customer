package com.sk.bookz_customer.validation.rules;

import com.sk.bookz_customer.constants.CustomerStatus;

public interface IStatusRule extends CustomerValidationRule{

    public boolean validate(CustomerStatus status);
}
