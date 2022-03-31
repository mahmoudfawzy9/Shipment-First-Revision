package com.company.star.service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.apptcom.kidstar.exception.ApiSubError;
import com.apptcom.kidstar.exception.ApiValidationError;
import com.apptcom.kidstar.exception.type.ValidationErrorException;
import com.company.star.form.Form;
import com.company.star.utils.Constants;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class GeneralValidation {
    
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	public void validateData(Form data) {
		Set<ConstraintViolation<Form>> violations = validator.validate(data);
		if (!violations.isEmpty()) {
			ArrayList<ApiSubError> errors = new ArrayList<>();
			for (ConstraintViolation<Form> violation : violations) {
				ApiValidationError err = createErrorFromViolation(violation);
				//l.info(err.getField());
				errors.add(err);

			}
			throw new ValidationErrorException(Constants.MESSAGE_INVALID_DATA, errors);
		}
	}

	private ApiValidationError createErrorFromViolation(ConstraintViolation<?> violation) {
		ApiValidationError err = new ApiValidationError();
		err.setObject(violation.getRootBean().getClass().toString());
		err.setField(violation.getPropertyPath().toString());
		err.setRejectedValue(violation.getInvalidValue());
		err.setMessage(violation.getMessage());
		return err;

	}

	private ApiValidationError createError(String object, String field, Object rejectedValue, String message) {
		ApiValidationError err = new ApiValidationError();
		err.setObject(object);
		err.setField(field);
		err.setRejectedValue(rejectedValue);
		err.setMessage(message);
		return err;

	}
    
}
