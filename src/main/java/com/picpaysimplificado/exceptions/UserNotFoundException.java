package com.picpaysimplificado.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Long id) {
		super("Usuário com o id '" + id + "' não encontrado");
	}

	public UserNotFoundException(String message) {
		super(message);
	}
}
