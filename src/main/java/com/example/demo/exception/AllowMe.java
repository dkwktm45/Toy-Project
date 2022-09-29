package com.example.demo.exception;

public class AllowMe extends SuperException {
	private static final String MESSAGE = "죄송합니다. 자기 자신은 참여할 수 없습니다.";

	public AllowMe(){
		super(MESSAGE);
	}

	public AllowMe(String name, String message){
		super(MESSAGE);
		addValidation(name,message);
	}

	@Override
	public int getStatusCode(){
		return 500;
	}
}
