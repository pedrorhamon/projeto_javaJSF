package com.starking.cerveja.exception;

public class CpfClienteJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CpfClienteJaCadastradoException(String msg) {
		super(msg);
	}
}
