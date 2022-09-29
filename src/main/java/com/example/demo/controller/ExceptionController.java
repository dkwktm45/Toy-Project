package com.example.demo.controller;

import com.example.demo.exception.SuperException;
import com.example.demo.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(SuperException.class)
	public ResponseEntity<ErrorResponse> handle404(SuperException e){
		int statusCode = e.getStatusCode();
		ErrorResponse body = ErrorResponse.builder()
				.code(String.valueOf(statusCode))
				.message(e.getMessage())
				.validation(e.getValidation())
				.build();
		return ResponseEntity.badRequest().body(body);
	}
}
