package com.starking.cerveja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.starking.cerveja.model.enums.TipoPessoa;
import com.starking.cerveja.repositories.EstadoRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private EstadoRepository estadoRepository;

	@RequestMapping("novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", this.estadoRepository.findAll());
		return mv;
	}
}
