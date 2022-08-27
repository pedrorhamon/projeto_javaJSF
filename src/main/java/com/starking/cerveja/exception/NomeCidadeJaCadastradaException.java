package com.starking.cerveja.exception;

public class NomeCidadeJaCadastradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NomeCidadeJaCadastradaException(String msg) {
		super(msg);
	}
}
