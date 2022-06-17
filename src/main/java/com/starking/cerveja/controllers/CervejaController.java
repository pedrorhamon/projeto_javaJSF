package com.starking.cerveja.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.starking.cerveja.model.Cerveja;

@Controller
public class CervejaController {
	
	@RequestMapping("/cervejas/novo")
	public String novo() {
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/cerveja/novo" , method = RequestMethod.POST)
	public void cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("mensagem", "Erro no Formulario");
			
		}
	}
}
