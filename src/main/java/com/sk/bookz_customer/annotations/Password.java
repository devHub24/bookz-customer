package com.sk.bookz_customer.annotations;
import com.sk.bookz_customer.validation.MyPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyPasswordValidator.class)
public @interface Password {
    String message() default "Passwords don't match our policy";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
