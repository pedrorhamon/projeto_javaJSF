package com.starking.cerveja.exception;

public class EmailUsuarioJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailUsuarioJaCadastradoException(String msg) {
		super(msg);
	}
}
