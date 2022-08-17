package com.sundar.time.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sundar.time.exception.ApplicationException;
import com.sundar.time.exception.BusinessException;
import com.sundar.time.exception.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDetails> businessExceptionHandler(BusinessException businessException,
			WebRequest webRequest) {
		ErrorDetails error = new ErrorDetails(businessException.getErrorCode(), businessException.getErrorMessage(),
				new Date(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorDetails> applicationExceptionHandler(ApplicationException applicationException,
			WebRequest webRequest) {
		ErrorDetails error = new ErrorDetails(applicationException.getErrorCode(),
				applicationException.getErrorMessage(), new Date(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
