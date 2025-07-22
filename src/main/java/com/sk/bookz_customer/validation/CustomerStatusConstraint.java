package com.sk.bookz_customer.validation;

import com.sk.bookz_customer.annotations.Status;
import com.sk.bookz_customer.constants.CustomerStatus;
import com.sk.bookz_customer.validation.rules.CustomerStatusRule;
import com.sk.bookz_customer.validation.rules.IStatusRule;
import com.sk.bookz_customer.validation.rules.RuleResult;
import com.sk.bookz_customer.validation.validators.CustomerStatusValidator;
import com.sk.bookz_customer.validation.validators.CustomerValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CustomerStatusConstraint implements ConstraintValidator<Status, CustomerStatus> {
    @Override
    public boolean isValid(CustomerStatus customerStatus, ConstraintValidatorContext constraintValidatorContext) {
        List<IStatusRule> statusRules = List.of(new CustomerStatusRule());
        CustomerValidator statusValidator = new CustomerStatusValidator(customerStatus, statusRules);
        RuleResult ruleResult = customerStatus!=null ? statusValidator.validate():new RuleResult();

        if(!ruleResult.isValid()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ruleResult.getMessage()).addConstraintViolation();
        }
        return ruleResult.isValid();
    }
}
