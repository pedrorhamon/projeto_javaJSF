package com.starking.cerveja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ModelAndView adicionarItem(Cerveja cerveja) {
		tabelaItensVenda.adicionarItem(cerveja, 1);
		return mvTabelaItensVendas();
	}
	
	@PutMapping("/item/{id}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("id") Cerveja cerveja, Integer quantidade) {
		this.tabelaItensVenda.alterarQuantidadeItens(cerveja, quantidade);
		return mvTabelaItensVendas();
	}
	
	@DeleteMapping("/item/{id}")
	public ModelAndView excluirItem(@PathVariable("id") Cerveja cerveja) {
		this.tabelaItensVenda.excluirItem(cerveja);
		return mvTabelaItensVendas();
	}

	private ModelAndView mvTabelaItensVendas() {
		ModelAndView mv = new ModelAndView("vendas/TabelaItensVenda");
		mv.addObject("itens", tabelaItensVenda.getItens());
		return mv;
	}

}