package com.sk.bookz_customer.validation;

import com.sk.bookz_customer.annotations.DateOfBirth;
import com.sk.bookz_customer.validation.rules.BirthDateRule;
import com.sk.bookz_customer.validation.rules.DateRule;
import com.sk.bookz_customer.validation.rules.RuleResult;
import com.sk.bookz_customer.validation.validators.BirthDateValidator;
import com.sk.bookz_customer.validation.validators.CustomerValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.List;

public class BirthDateConstraint implements ConstraintValidator<DateOfBirth, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        List<DateRule> dateRules = List.of(new BirthDateRule());
        CustomerValidator validator = new BirthDateValidator(localDate,dateRules);
        RuleResult ruleResult = localDate!=null? validator.validate(): new RuleResult();
        if(!ruleResult.isValid()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ruleResult.getMessage()).addConstraintViolation();

        }
        return ruleResult.isValid();
    }
}
