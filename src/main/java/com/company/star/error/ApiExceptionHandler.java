package com.company.star.error;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import com.company.star.exception.FcmException;
import org.apache.commons.cli.MissingArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.apptcom.kidstar.exception.ApiError;
import com.apptcom.kidstar.exception.type.ObjectNotFoundException;
import com.apptcom.kidstar.exception.type.ValidationErrorException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger l = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
//        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
//        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		l.error(ex.getMessage());
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	protected ResponseEntity<Object> handleObjectNotFound(ObjectNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		l.error(ex.getMessage(), ex);
		apiError.setMessage(ex.getCustomMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(MissingArgumentException.class)
	protected ResponseEntity<Object> handleMissingArgument(MissingArgumentException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		l.error(ex.getMessage());
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(ValidationErrorException.class)
	protected ResponseEntity<Object> handleInvalidData(ValidationErrorException ex) {
		ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY);
		apiError.setMessage(ex.getCustomMessage());
		apiError.setDebugMessage(ex.getMessage());
		apiError.setSubErrors(ex.getParams());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(IOException.class)
	protected ResponseEntity<Object> handleIO(IOException ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		l.error(ex.getMessage());
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(FcmException.class)
	protected ResponseEntity<Object> handleFireBase(FcmException ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		l.error(ex.getMessage());
		apiError.setMessage(ex.getMessage());
		apiError.setStatus(ex.getStatus().value());
		apiError.setDebugMessage("Token Id = " + ex.getTokenId());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getError());
	}

}
