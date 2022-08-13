package com.starking.cerveja.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/cervejas";
		}
		
		return "Login";
	}
	
	@GetMapping("/403")
	public String acessoNegado() {
		return "403";
	}
	
	@GetMapping("/404")
	public String paginaNaoEncontrada() {
		return "404";
	}
	
	@GetMapping("/500")
	public String errorDoServidor() {
		return "500";
	}
}
