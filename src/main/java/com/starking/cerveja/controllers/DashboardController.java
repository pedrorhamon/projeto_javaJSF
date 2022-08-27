package com.starking.cerveja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.starking.cerveja.repositories.CervejaRepository;
import com.starking.cerveja.repositories.ClienteRepository;
import com.starking.cerveja.repositories.VendaRepository;

@Controller
public class DashboardController {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("vendasNoAno", this.vendaRepository.valorTotalNoAno());
		mv.addObject("vendasNoMes", this.vendaRepository.valorTotalNoMes());
		mv.addObject("ticketMedio", this.vendaRepository.valorTicketMedioNoAno());
		
		mv.addObject("valorItensEstoque", this.cervejaRepository.valorItensEstoque());
		mv.addObject("totalClientes", this.clienteRepository.count());
		
		return mv;
	}
}
