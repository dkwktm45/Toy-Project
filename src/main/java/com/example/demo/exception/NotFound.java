package com.example.demo.exception;

public class NotFound extends SuperException {
	private static final String MESSAGE = "존재하지 않습니다.";

	public NotFound(){
		super(MESSAGE);
	}

	public NotFound(String name, String message){
		super(MESSAGE);
		addValidation(name,message);
	}

	@Override
	public int getStatusCode(){
		return 400;
	}
}