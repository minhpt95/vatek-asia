package com.catdev.project.validator.anotation;

import com.catdev.project.validator.CustomPatternValidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author itsol.hungtt on 1/25/2021
 */
@Documented
@Constraint(validatedBy = CustomPatternValidators.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CustomPatternConstraint {

    String value();

    String message() default "Must match value defined";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
