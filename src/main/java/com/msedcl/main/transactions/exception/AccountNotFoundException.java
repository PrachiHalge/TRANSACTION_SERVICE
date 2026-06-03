package com.msedcl.main.transactions.exception;

public class AccountNotFoundException extends RuntimeException{
	
	public AccountNotFoundException(String message) {
		super(message);
	}

}
