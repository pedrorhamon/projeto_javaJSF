package com.starking.cerveja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioController {
	
	@RequestMapping("/usuarios/novo")
	public String novo() {
		return "usuario/CadastroUsuario";
	}
}
