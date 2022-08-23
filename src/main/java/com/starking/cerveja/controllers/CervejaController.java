package com.starking.cerveja.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starking.cerveja.controllers.page.PageWrapper;
import com.starking.cerveja.exception.ImpossivelExcluirEntidadeException;
import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.model.dto.CervejaDTO;
import com.starking.cerveja.model.enums.Origem;
import com.starking.cerveja.model.enums.Sabor;
import com.starking.cerveja.repositories.CervejaRepository;
import com.starking.cerveja.repositories.EstiloRepository;
import com.starking.cerveja.repositories.filter.CervejaFilter;
import com.starking.cerveja.services.CadastroCervejaService;

@Controller
@RequestMapping("/cervejas")
public class CervejaController {
	
	@Autowired
	private CadastroCervejaService cervejaService;
	
	@Autowired
	private EstiloRepository estiloRepository;
	
	@Autowired
	private CervejaRepository cervejaRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estiloRepository.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}
	
	@RequestMapping(value = {"/novo", "{\\d+}"}, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cerveja);
		}
		
		this.cervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		return new ModelAndView("redirect:/cervejas/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result, @PageableDefault(size =2) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cerveja/PesquisaCerveja");
		mv.addObject("estilos", this.estiloRepository.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		
		
		PageWrapper<Cerveja> paginaWrapper = new PageWrapper<>(this.cervejaRepository.filtrar(cervejaFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CervejaDTO> pesquisar(String skuOuNome) {
		return this.cervejaRepository.porSkuOuNome(skuOuNome);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Cerveja cerveja) {
		
		try {			
			this.cervejaService.excluir(cerveja);
		}catch(ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Cerveja cerveja) {
		ModelAndView mv = this.novo(cerveja);
		mv.addObject(cerveja);
		return mv;
	}	
}
