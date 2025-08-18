package com.bivago_api.shared.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bivago_api.shared.validators.PasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = { PasswordValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    
    String message() default "Senha deve conter pelo menos 1 letra minúscula, 1 letra maíscula, 1 número, 1 caractér especial, e está entre 8 e 32 caractéres";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
