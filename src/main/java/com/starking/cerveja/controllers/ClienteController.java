package com.starking.cerveja.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starking.cerveja.controllers.page.PageWrapper;
import com.starking.cerveja.exception.CpfClienteJaCadastradoException;
import com.starking.cerveja.model.Cliente;
import com.starking.cerveja.model.enums.TipoPessoa;
import com.starking.cerveja.repositories.ClienteRepository;
import com.starking.cerveja.repositories.EstadoRepository;
import com.starking.cerveja.repositories.filter.ClienteFilter;
import com.starking.cerveja.services.CadastroClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping("novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", this.estadoRepository.findAll());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}

		try {
			this.clienteService.salvar(cliente);
		} catch (CpfClienteJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return this.novo(cliente);
		}
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		return new ModelAndView("redirect:/clientes/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result,
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cliente/PesquisaCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());

		PageWrapper<Cliente> paginaWrapper = new PageWrapper<>(this.clienteRepository.filtrar(clienteFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
}
