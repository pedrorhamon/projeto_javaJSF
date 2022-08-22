package com.starking.cerveja.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starking.cerveja.config.security.UsuarioSystem;
import com.starking.cerveja.controllers.page.PageWrapper;
import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.model.Venda;
import com.starking.cerveja.model.enums.StatusVenda;
import com.starking.cerveja.model.enums.TipoPessoa;
import com.starking.cerveja.repositories.CervejaRepository;
import com.starking.cerveja.repositories.VendaRepository;
import com.starking.cerveja.repositories.filter.VendaFilter;
import com.starking.cerveja.services.CadastroVendaService;
import com.starking.cerveja.session.TabelaItensSession;
import com.starking.cerveja.validation.VendaValidator;

@Controller
@RequestMapping("/vendas")
public class VendaController {
	
	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private TabelaItensSession tabelaItensSession;
	
	@Autowired
	private CadastroVendaService vendaService;
	
	@Autowired
	private VendaValidator vendaValidator;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@InitBinder
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(vendaValidator);
	}
	
	@GetMapping("/nova")
	public ModelAndView nova(Venda venda) {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		
		if(StringUtils.isEmpty(venda.getUuid())) {			
			mv.addObject("uuid", UUID.randomUUID().toString());
		}
		
		mv.addObject("itens", venda.getItens());
		mv.addObject("valorFrete", venda.getValorFrete());
		mv.addObject("valorDesconto", venda.getValorDesconto());
		mv.addObject("valorTotalItens", tabelaItensSession.getValorTotal(venda.getUuid()));
		return mv;
	}
	
	@PostMapping(value = "/nova", params = "salvar")
	public ModelAndView salvar(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSystem usuarioSystem) {
		
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}
		
		venda.setUsuario(usuarioSystem.getUsuario());
		
		vendaService.salvar(venda);
		attributes.addFlashAttribute("mensagem", "Venda salva com sucesso");
		return new ModelAndView("redirect:/vendas/nova");
	}

	@PostMapping(value = "/nova", params = "emitir")
	public ModelAndView emitir(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSystem usuarioSystem) {
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}
		
		venda.setUsuario(usuarioSystem.getUsuario());
		
		vendaService.emitir(venda);
		attributes.addFlashAttribute("mensagem", "Venda emitida com sucesso");
		return new ModelAndView("redirect:/vendas/nova");
	}
	
	@PostMapping(value = "/nova", params = "enviarEmail")
	public ModelAndView enviarEmail(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSystem usuarioSystem) {
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}
		
		venda.setUsuario(usuarioSystem.getUsuario());
		
		vendaService.salvar(venda);
		attributes.addFlashAttribute("mensagem", "Venda salva e e-mail enviado");
		return new ModelAndView("redirect:/vendas/nova");
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja, String uuid) {
		Cerveja cerveja = this.cervejaRepository.findOne(codigoCerveja);
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
	
	@GetMapping
	public ModelAndView pesquisar(VendaFilter vendaFilter,
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/venda/PesquisaVendas");
		mv.addObject("todosStatus", StatusVenda.values());
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		PageWrapper<Venda> paginaWrapper = new PageWrapper<>(this.vendaRepository.filtrar(vendaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	private ModelAndView mvTabelaItensVendas(String uuid) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItensSession.getItens(uuid));
		mv.addObject("valorTotal", tabelaItensSession.getValorTotal(uuid));
		return mv;
	}
	
	private void validarVenda(Venda venda, BindingResult result) {
		venda.adicionarItens(tabelaItensSession.getItens(venda.getUuid()));
		venda.calcularValorTotal();
		
		vendaValidator.validate(venda, result);
	}
}