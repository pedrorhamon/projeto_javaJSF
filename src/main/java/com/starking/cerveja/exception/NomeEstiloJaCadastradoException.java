package com.starking.cerveja.exception;

public class NomeEstiloJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NomeEstiloJaCadastradoException(String msg) {
		super(msg);
	}
}
