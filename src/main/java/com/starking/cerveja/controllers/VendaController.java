package com.starking.cerveja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.repositories.CervejaRepository;
import com.starking.cerveja.session.TabelaItensVenda;

@Controller
@RequestMapping("/vendas")
public class VendaController {
	
	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private TabelaItensVenda tabelaItensVenda;
	
	@GetMapping("/nova")
	public String nova() {
		return "vendas/CadastroVenda";
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja) {
		Cerveja cerveja = this.cervejaRepository.findOne(codigoCerveja);
		tabelaItensVenda.adicionarItem(cerveja, 1);
		ModelAndView mv = new ModelAndView("vendas/TabelaItensVenda");
		mv.addObject("itens", tabelaItensVenda.getItens());
		return mv;
	}

}