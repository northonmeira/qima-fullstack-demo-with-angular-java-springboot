package br.com.northon.qimatechfullstackdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredCategoryIdIsNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequiredCategoryIdIsNullException(String message) {
		super(message);
	}
	
	public RequiredCategoryIdIsNullException() {
		super("Category ID is required!");
	}

}