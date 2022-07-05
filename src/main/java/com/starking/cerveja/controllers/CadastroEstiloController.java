package com.starking.cerveja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CadastroEstiloController {
	
	@RequestMapping("/estilos/novo")
	public String novo() {
		return "estilo/CadastroEstilo";
	}
}
