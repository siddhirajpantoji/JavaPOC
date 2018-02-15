package com.travelex.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.travelex.constants.MessageConstants;
import com.travelex.response.BaseResponse;
import com.travelex.response.ValidationResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

//	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());
	
	@ExceptionHandler(AuthException.class)
	public  BaseResponse handleAuthResponse(AuthException exception)
	{
//		LOGGER.error("Unauthorised Access ");
		return new BaseResponse(HttpStatus.FORBIDDEN, exception.getMessage());
	}
	
	
	
	
	@ExceptionHandler ( value = MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationResponse> handleMethodNotValidException ( MethodArgumentNotValidException exception)
	{
		Map<String,String> errorMap = null;
		BindingResult bindingResult = exception.getBindingResult();
		if(! CollectionUtils.isEmpty(bindingResult.getAllErrors()))
		{
			errorMap= new HashMap<>();
			for (Object object : bindingResult.getAllErrors()) {
				if(object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
				}
			}
			if( errorMap.size() >0)
			{
				return new ResponseEntity<ValidationResponse>(new ValidationResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_REQUEST, errorMap), HttpStatus.BAD_REQUEST);		
			}
		}
		return new ResponseEntity<ValidationResponse>(new ValidationResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_REQUEST), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler ( value = ConstraintViolationException.class)
	public ResponseEntity<ValidationResponse> handleConstraintViolationException ( ConstraintViolationException exception)
	{
		Map<String,String> errorMap = null;
		Set<ConstraintViolation<?>> violations= exception.getConstraintViolations();
		errorMap= new HashMap<>();
		for(ConstraintViolation<?> constraintViolation:violations)
		{
			errorMap.put(constraintViolation.getRootBeanClass().getName()+"."+constraintViolation.getPropertyPath(), constraintViolation.getMessage());
		}
		return new ResponseEntity<ValidationResponse>(new ValidationResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_REQUEST, errorMap), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> handleAllResponse(Exception exception)
	{
		exception.printStackTrace();
//		LOGGER.error("Exception occured ",exception);
		return new ResponseEntity<BaseResponse>(new  BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<BaseResponse> handleAllResponse(MissingServletRequestParameterException exception)
	{
//		LOGGER.error("MissingServletRequestParameterException occured ",exception);
		return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.BAD_REQUEST, exception.getMessage()),HttpStatus.BAD_REQUEST);
	}
}
