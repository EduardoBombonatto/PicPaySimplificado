package com.picpaysimplificado.exceptions;

@SuppressWarnings("serial")
public class UserTypeNotAuthorizeSenderException extends RuntimeException {
	public UserTypeNotAuthorizeSenderException() {
		super("Lojistas não estão autorizados a realizar transações.");
	}
	
	public UserTypeNotAuthorizeSenderException(String message) {
		super(message);
	}
}
