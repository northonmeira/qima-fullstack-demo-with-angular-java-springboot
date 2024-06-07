package br.com.northon.qimatechfullstackdemo.exceptions.handler;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.northon.qimatechfullstackdemo.exceptions.ExceptionResponse;
import br.com.northon.qimatechfullstackdemo.exceptions.InvalidJwtAuthenticationException;
import br.com.northon.qimatechfullstackdemo.exceptions.RequiredCategoryIdIsNullException;
import br.com.northon.qimatechfullstackdemo.exceptions.RequiredObjectIsNullException;
import br.com.northon.qimatechfullstackdemo.exceptions.ResourceNotFoundException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, 
			WebRequest request) {
				ExceptionResponse exceptionResponse = 
						new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
						
						
				return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleResourceNotFoundExceptions(Exception ex, 
			WebRequest request) {
				ExceptionResponse exceptionResponse = 
						new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
						
						
				return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleRequiredObjectIsNullException(Exception ex, 
			WebRequest request) {
				ExceptionResponse exceptionResponse = 
						new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
						
						
				return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RequiredCategoryIdIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleRequiredCategoryIdIsNullException(Exception ex, 
			WebRequest request) {
				ExceptionResponse exceptionResponse = 
						new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
						
						
				return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationExceptions(Exception ex, 
			WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(Exception ex, 
			WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
