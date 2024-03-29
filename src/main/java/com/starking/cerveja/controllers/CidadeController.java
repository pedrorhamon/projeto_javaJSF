package com.starking.cerveja.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starking.cerveja.controllers.page.PageWrapper;
import com.starking.cerveja.exception.ImpossivelExcluirEntidadeException;
import com.starking.cerveja.exception.NomeCidadeJaCadastradaException;
import com.starking.cerveja.model.Cidade;
import com.starking.cerveja.repositories.CidadeRepository;
import com.starking.cerveja.repositories.EstadoRepository;
import com.starking.cerveja.repositories.filter.CidadeFilter;
import com.starking.cerveja.services.CadastrarCidadeService;


@Controller
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastrarCidadeService cidadeService;
	
	@RequestMapping("novo")
	public ModelAndView novo(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade/CadastroCidade");
		mv.addObject("estados", this.estadoRepository.findAll());
		return mv;
	}
	
	@PostMapping({"/nova", "/nova/{codgo}"})
	@CacheEvict(value = "cidades", key = "#cidade.estado.codigo", condition = "#cidade.temEstado()")
	public ModelAndView salvar(@Valid Cidade cidade, Model model, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cidade);
		}
		
		try {
			this.cidadeService.salvar(cidade);
		} catch (NomeCidadeJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(cidade);
		}
		
		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
		return new ModelAndView("redirect:/cidades/nova");
	}
	
	@Cacheable(value = "cidades", key = "#codigoEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(@RequestParam(name ="estado", defaultValue = "-1" ) Long codigoEstado) {
		try {
			Thread.sleep(500);
		} catch(InterruptedException e) {	}		
			return this.cidadeRepository.findByEstadoCodigo(codigoEstado);
	}	
	
	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cidade/PesquisaCidade");
		
		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(this.cidadeRepository.filtrar(cidadeFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> excluir(@PathVariable("codigo") Cidade cidade) {
		try {
			this.cidadeService.excluir(cidade);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView atualizar(@PathVariable("codigo") Cidade cidade) {
		ModelAndView mv = this.novo(cidade);
		mv.addObject(this.cidadeRepository.findByCodigoFetchingEstado(cidade.getCodigo()));
		return mv;
	}
}
