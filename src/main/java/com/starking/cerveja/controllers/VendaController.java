package com.starking.cerveja.controllers;

import java.util.UUID;

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
import com.starking.cerveja.session.TabelaItensSession;

@Controller
@RequestMapping("/vendas")
public class VendaController {
	
//	@Autowired
//	private CervejaRepository cervejaRepository;
	
	@Autowired
	private TabelaItensSession tabelaItensSession;
	
	@GetMapping("/nova")
	public ModelAndView nova() {
		ModelAndView mv = new ModelAndView("vendas/CadastroVenda");
		mv.addObject("uuid", UUID.randomUUID().toString());
		return mv;
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Cerveja cerveja, String uuid) {
		tabelaItensSession.adicionarItem(uuid, cerveja, 1);
		return mvTabelaItensVendas(uuid);
	}
	
	@PutMapping("/item/{id}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("id") Cerveja cerveja, Integer quantidade, String uuid) {
		this.tabelaItensSession.alterarQuantidadeItens(uuid, cerveja, quantidade);
		return mvTabelaItensVendas(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{id}")
	public ModelAndView excluirItem(@PathVariable("id") Cerveja cerveja, @PathVariable String uuid) {
		this.tabelaItensSession.excluirItem(uuid, cerveja);
		return mvTabelaItensVendas(uuid);
	}

	private ModelAndView mvTabelaItensVendas(String uuid) {
		ModelAndView mv = new ModelAndView("vendas/TabelaItensVenda");
		mv.addObject("itens", tabelaItensSession.getItens(uuid));
		mv.addObject("valorTotal", tabelaItensSession.getValorTotal(uuid));
		return mv;
	}

}