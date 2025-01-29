package com.picpaysimplificado.exceptions;

@SuppressWarnings("serial")
public class InsuficientAmountException extends RuntimeException{
	public InsuficientAmountException() {
		super("Saldo insuficiente.");
	}
	
	public InsuficientAmountException(String message) {
		super(message);
	}
}

