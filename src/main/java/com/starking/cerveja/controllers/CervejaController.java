package com.starking.cerveja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.model.enums.OrigemEnum;
import com.starking.cerveja.model.enums.SaborEnum;
import com.starking.cerveja.repositories.CervejaRepository;
import com.starking.cerveja.repositories.EstiloRepository;

@Controller
public class CervejaController {
	
	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private EstiloRepository estiloRepository;
	
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja) {
//		Optional<Cerveja> cervejaOptional = cervejaRepository.findBySkuIgnoreCase("111154");
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", SaborEnum.values());
		mv.addObject("estilos", this.estiloRepository.findAll());
		mv.addObject("origens", OrigemEnum.values());
		return mv;
	}
	
	@RequestMapping(value = "/cerveja/novo" , method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return novo(cerveja);
		}
		redirectAttributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso");
		return new ModelAndView("redirect:/cerveja/novo");
	}
}
