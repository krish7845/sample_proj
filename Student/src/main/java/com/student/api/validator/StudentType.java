package com.student.api.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = StudentTypeValidator.class)
@Documented
public @interface StudentType {

	String message() default "Student Type must be one of these New, Re-Enroll, Completed.";

    Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};}
