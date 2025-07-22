package com.sk.bookz_customer.validation.validators;


import com.sk.bookz_customer.validation.rules.PhoneRule;
import com.sk.bookz_customer.validation.rules.RuleResult;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberValidator implements CustomerValidator {
    private String phoneNumber;
    private List<PhoneRule> rules;

    @Override
    public RuleResult validate() {
        StringBuilder message = new StringBuilder();
        RuleResult ruleResult = new RuleResult();
    if(this.phoneNumber == null ) {
        for (PhoneRule rule : this.rules) {
            if (!rule.validate(this.phoneNumber)) {
                ruleResult.setValid(false);
             message.append(rule.getErrorMessage()).append(", ");
            }
        }
    }
        ruleResult.setMessage(message.toString());
        return ruleResult;
    }
}
