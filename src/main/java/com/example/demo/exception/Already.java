package com.example.demo.exception;

public class Already extends SuperException  {
	private static final String MESSAGE = "이미 참여하신 유저입니다.";

	public Already(){
		super(MESSAGE);
	}

	public Already(String name, String message){
		super(MESSAGE);
		addValidation(name,message);
	}

	@Override
	public int getStatusCode(){
		return 500;
	}
}
