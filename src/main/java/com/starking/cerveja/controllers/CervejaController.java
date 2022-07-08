package com.starking.cerveja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.repositories.CervejaRepository;

@Controller
public class CervejaController {
	
	@Autowired
	private CervejaRepository cervejaRepository;
	
	@RequestMapping("/cervejas/novo")
	public String novo(Cerveja cerveja) {
//		Optional<Cerveja> cervejaOptional = cervejaRepository.findBySkuIgnoreCase("111154");
		
		return "cerveja/CadastroCerveja";
	}
	
//	@RequestMapping(value = "/cerveja/novo" , method = RequestMethod.POST)
//	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
//		if(result.hasErrors()) {
//			return novo(cerveja);
//		}
//		redirectAttributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso");
//		return "redirect:/cerveja/novo";
//	}
}
