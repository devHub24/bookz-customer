package com.sk.bookz_customer.validation.rules;

import com.sk.bookz_customer.constants.CustomerStatus;

import java.util.Arrays;
import java.util.List;

public class CustomerStatusRule implements IStatusRule{

    private List<CustomerStatus> customerStatuses = Arrays.asList(CustomerStatus.values());

    @Override
    public boolean validate(CustomerStatus status) {
        return customerStatuses.contains(status);
    }

    @Override
    public String getErrorMessage() {
        return "Invalid Customer Status";
    }
}
