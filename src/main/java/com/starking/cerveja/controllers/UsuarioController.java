package com.starking.cerveja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starking.cerveja.exception.EmailJaCadastradoException;
import com.starking.cerveja.exception.SenhaObrigatoriaException;
import com.starking.cerveja.model.Usuario;
import com.starking.cerveja.repositories.GrupoRepository;
import com.starking.cerveja.services.CadastrarUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private CadastrarUsuarioService usuarioService;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@RequestMapping("novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", this.grupoRepository.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
			this.usuarioService.salvar(usuario);
		}catch(EmailJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return this.novo(usuario);
		}catch(SenhaObrigatoriaException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return this.novo(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/usuarios/novo");
	}
}
