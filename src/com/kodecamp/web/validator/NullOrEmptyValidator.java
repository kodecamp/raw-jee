package com.kodecamp.web.validator;

public class NullOrEmptyValidator implements IValidator{

	@Override
	public boolean validate(String value) {
		return "".equals(value) ? false : true; 
	}

}
