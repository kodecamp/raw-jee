package com.kodecamp.web.validator;

public class ValidInputValidator implements IValidator {

	private final String  PATTERN = "^[a-zA-z ]*$";
	private IValidator validator;
	
	public ValidInputValidator(final IValidator validator) {
		this.validator = validator;
	}
	
	@Override
	public boolean validate(String value) {
		return validator.validate(value) ? value.trim().matches(PATTERN) : false;
	}
}
