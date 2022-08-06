package com.starking.cerveja.exception;

public class SenhaObrigatoriaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SenhaObrigatoriaException(String msg) {
		super(msg);
	}
}
