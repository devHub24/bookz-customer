package com.sk.bookz_customer.validation.validators;

import com.sk.bookz_customer.validation.rules.PasswordRule;
import com.sk.bookz_customer.validation.rules.RuleResult;

import java.util.List;

public class CustomPasswordValidator implements CustomerValidator {

    private String password;
    private List<PasswordRule> passwordRules;


    public  CustomPasswordValidator(String password, List<PasswordRule> passwordRules) {
        this.password = password;
        this.passwordRules = passwordRules;

    }

    @Override
    public RuleResult validate() {

        RuleResult ruleResult = new RuleResult();
        StringBuilder messages = new StringBuilder();
        if(this.password != null && !this.password.isEmpty()) {
            for (PasswordRule passwordRule : this.passwordRules) {
                if (!passwordRule.validate(this.password)) {
                    ruleResult.setValid(false);
                    messages.append(passwordRule.getErrorMessage()).append(", ");
                }
            }
        }

        ruleResult.setMessage(messages.toString());
        return ruleResult;
    }
}
