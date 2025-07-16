package com.sk.bookz_customer.validation.validators;

import com.sk.bookz_customer.validation.rules.DateRule;
import com.sk.bookz_customer.validation.rules.RuleResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class BirthDateValidator implements CustomerValidator{

    private LocalDate birthDate;
    private List<DateRule> dateRules;

    public BirthDateValidator(LocalDate birthDate, List<DateRule> dateRules) {
        this.birthDate = birthDate;
        this.dateRules = dateRules;
    }

    @Override
    public RuleResult validate() {
        RuleResult ruleResult = new RuleResult();
        StringBuilder messages = new StringBuilder();
        for(DateRule dateRule : this.dateRules){
            if(!dateRule.validate(this.birthDate)){
                ruleResult.setValid(false);
                messages.append(dateRule.getErrorMessage()).append(", ");
            }
        }
        ruleResult.setMessage(messages.toString());
        return ruleResult;
    }
}
