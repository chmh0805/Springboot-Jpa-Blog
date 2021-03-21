package com.hyuk.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Component
public class ExceptionAdvice {

	@ExceptionHandler(BindException.class)
	public String processValidationError(BindException exception) {
		StringBuffer buffer = new StringBuffer();
		
		BindingResult bindingResult = exception.getBindingResult();

		Map<String, String> errors = new HashMap<>();

		for (FieldError error : bindingResult.getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
			buffer.append(error.getField() + " : " + error.getDefaultMessage() + "\n");
		}
		
		return buffer.toString();
	}
}
