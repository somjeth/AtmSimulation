package com.mydomain.model;

public class AtmWithdrawException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public AtmWithdrawException(String message) {
		super(message);
	}
	
}
