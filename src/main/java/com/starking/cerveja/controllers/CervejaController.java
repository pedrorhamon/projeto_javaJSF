package com.starking.cerveja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.starking.cerveja.model.Cerveja;

@Controller
public class CervejaController {
	
	@RequestMapping("/cervejas/novo")
	public String novo(Cerveja cerveja) {
		return "cerveja/CadastroCerveja";
	}
	
//	@RequestMapping(value = "/cerveja/novo" , method = RequestMethod.POST)
//	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
//		if(result.hasErrors()) {
//			return novo(cerveja);
//		}
//		
//		redirectAttributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso");
//		System.out.println(">>>> sku: " + cerveja.getSku());
//		return "redirect:/cerveja/novo";
//	}
}
