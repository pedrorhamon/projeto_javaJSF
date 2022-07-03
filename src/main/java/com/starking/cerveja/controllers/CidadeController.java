package com.starking.cerveja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CidadeController {
	
	@RequestMapping("/cidades/novo")
	public String novo() {
		return "cidade/CadastroCidade";
	}
}
