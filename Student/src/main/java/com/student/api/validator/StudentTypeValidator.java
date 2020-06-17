package com.student.api.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentTypeValidator implements ConstraintValidator<StudentType, String> {

	List<String> transmissionType = Arrays.asList("New", "Re-Enroll","Completed");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return transmissionType.contains(value);
	}
}
