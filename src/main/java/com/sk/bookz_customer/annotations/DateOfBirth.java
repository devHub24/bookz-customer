package com.sk.bookz_customer.annotations;

import com.sk.bookz_customer.validation.BirthDateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= BirthDateValidation.class)
public @interface DateOfBirth {

    String message() default "Birth date is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
