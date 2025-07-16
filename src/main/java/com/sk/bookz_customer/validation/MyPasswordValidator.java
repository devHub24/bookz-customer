package com.sk.bookz_customer.validation;

import com.sk.bookz_customer.annotations.Password;
import com.sk.bookz_customer.constants.EnglishCharacterSet;
import com.sk.bookz_customer.validation.rules.CharacterRule;
import com.sk.bookz_customer.validation.rules.LengthRule;
import com.sk.bookz_customer.validation.rules.PasswordRule;
import com.sk.bookz_customer.validation.rules.RuleResult;
import com.sk.bookz_customer.validation.validators.CustomPasswordValidator;
import com.sk.bookz_customer.validation.validators.CustomerValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;


public class MyPasswordValidator implements ConstraintValidator<Password, String> {

    private static final int MAX_LENGTH =30;
    private static final int MIN_LENGTH = 8;
    private static final int MAX_REPETITIVE_CHAR = 3;
    private static final int MIN_UPPERCASE_CHAR = 1;
    private static final int MIN_LOWERCASE_CHAR = 1;
    private static final int MIN_SPECIAL_CHAR = 1;
    private static final int MIN_DIGIT =1 ;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //Setting up rules
        List<PasswordRule> passwordRules = new ArrayList<>();
        passwordRules.add(new LengthRule(MIN_LENGTH, MAX_LENGTH));
        passwordRules.add(new CharacterRule(EnglishCharacterSet.UPPERCASE, MIN_UPPERCASE_CHAR));
        passwordRules.add(new CharacterRule(EnglishCharacterSet.LOWERCASE,MIN_LOWERCASE_CHAR));
        passwordRules.add(new CharacterRule(EnglishCharacterSet.DIGITS,MIN_DIGIT));


        CustomerValidator passwordValidator = new CustomPasswordValidator(s, passwordRules);
        RuleResult ruleResult = passwordValidator.validate();

        if(!ruleResult.isValid()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ruleResult.getMessage()).addConstraintViolation();
        }
        return ruleResult.isValid();
    }


//    @Override
//    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
 //       PasswordData passwordData = new PasswordData();
//        List<Rule> passwordRules = new ArrayList<>();
//        passwordRules.add(new LengthRule(MIN_LENGTH,MAX_LENGTH));
//        passwordRules.add(getCharacterRules());
//        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETITIVE_CHAR));
 //       CustomerValidator passwordValidator = new CustomerValidator(passwordRules);
 //       RuleResult result = passwordValidator.validate(passwordData);
//        return result.isValid();
//    }
//
//    private CharacterCharacteristicsRule getCharacterRules(){
//        CharacterCharacteristicsRule characteristicsRule = new CharacterCharacteristicsRule();
//        List<CharacterRule> characterRules = List.of(
//                new CharacterRule(EnglishCharacterData.UpperCase,MIN_UPPERCASE_CHAR),
//                new CharacterRule(EnglishCharacterData.LowerCase,MIN_LOWERCASE_CHAR),
//                new CharacterRule(EnglishCharacterData.Special,MIN_SPECIAL_CHAR),
//                new CharacterRule(EnglishCharacterData.Digit,MIN_DIGIT)
//        );
//        characteristicsRule.setRules(characterRules);
//        return characteristicsRule;
//    }

}
