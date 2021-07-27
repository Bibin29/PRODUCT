package com.target.retail.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.target.retail.model.ApiError;

@ControllerAdvice
public class ApiErrorAdviceController extends ResponseEntityExceptionHandler {
private static final Logger log = LoggerFactory.getLogger(ApiErrorAdviceController.class);
	
	
	@ExceptionHandler({ProductNotFoundException.class})
	protected ResponseEntity<Object> handleProductNotFoundEx(ProductNotFoundException ex, WebRequest request) {
		log.error("Requested record not found in database error {}", ex.getLocalizedMessage());
		ApiError error = new ApiError();
		error.setMessage(ex.getLocalizedMessage());
		error.setDeveloperMessage("Could not find the product for give id "+ex.getProductId());
		error.setCode("11000");
		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		log.error("Unknown error {}", ex.getLocalizedMessage());
		return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(GenericApiException.class)
	protected ResponseEntity<Object> handleGenericException(GenericApiException ex, WebRequest request) {
		log.error("GenericApiException error {}", ex.getLocalizedMessage());
		ApiError error = new ApiError();
		error.setMessage(ex.getLocalizedMessage());
		error.setDeveloperMessage("Error while processing operations  for give id "+ex.getProductId());
		error.setCode("11002");
		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductDataUpdateException.class)
	protected ResponseEntity<Object> handleProductDataUpdateException(ProductDataUpdateException ex, WebRequest request) {
		log.error("DataBaseUpdateException {}", ex.getLocalizedMessage());
		ApiError error = new ApiError();
		error.setMessage(ex.getLocalizedMessage());
		error.setCode("CodeToRepresentDataBaseUpdateException");
		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
