package com.sk.bookz_customer.validation.validators;

import com.sk.bookz_customer.constants.CustomerStatus;
import com.sk.bookz_customer.validation.rules.CustomerValidationRule;
import com.sk.bookz_customer.validation.rules.IStatusRule;
import com.sk.bookz_customer.validation.rules.RuleResult;

import java.util.List;

public class CustomerStatusValidator implements CustomerValidator {
    private CustomerStatus customerStatus;
    private List<IStatusRule> rules;

    public CustomerStatusValidator(CustomerStatus customerStatus, List<IStatusRule> rules) {
        this.customerStatus = customerStatus;
        this.rules = rules;
    }

    @Override
    public RuleResult validate() {
        RuleResult result = new RuleResult();
        StringBuilder message = new StringBuilder();
            for(IStatusRule rule: this.rules){
                if(!rule.validate(customerStatus)){
                    result.setValid(false);
                    message.append(rule.getErrorMessage()).append(", ");
                }
            }
            result.setMessage(message.toString());
        return result;
    }
}
