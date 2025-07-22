package com.sk.bookz_customer.validation;

import com.sk.bookz_customer.annotations.PhoneNumber;
import com.sk.bookz_customer.validation.rules.IndianPhoneRule;
import com.sk.bookz_customer.validation.rules.PhoneRule;
import com.sk.bookz_customer.validation.rules.RuleResult;
import com.sk.bookz_customer.validation.validators.CustomerValidator;
import com.sk.bookz_customer.validation.validators.PhoneNumberValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class PhoneNumberConstraint implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        List<PhoneRule> rules = List.of(
                new IndianPhoneRule()
        );

        CustomerValidator phoneNumberValidator = new PhoneNumberValidator(s,rules);
        RuleResult result = !s.isEmpty()?phoneNumberValidator.validate(): new RuleResult();
        if(!result.isValid()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(result.getMessage()).addConstraintViolation();
        }
        return result.isValid();
    }
}
