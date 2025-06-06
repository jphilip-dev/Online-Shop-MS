package com.jphilips.onlineshop.item.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueSkuValidator.class)
public @interface  UniqueSku {
    String message() default "SKU is already in use"; // this gets returned to the client or UI
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
