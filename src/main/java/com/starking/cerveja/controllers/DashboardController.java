package com.starking.cerveja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.starking.cerveja.repositories.VendaRepository;

@Controller
public class DashboardController {
	
	@Autowired
	private VendaRepository vendaRepository;
	

	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("vendasNoAno", vendaRepository.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendaRepository.valorTotalNoMes());
		mv.addObject("ticketMedio", vendaRepository.valorTicketMedioNoAno());
		
		return mv;
	}
}
