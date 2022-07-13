package com.starking.cerveja.controllers.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.starking.cerveja.exception.NomeEstiloException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(NomeEstiloException.class)
	public ResponseEntity<String> handleNomeEstiloJaCadastradoException(NomeEstiloException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
