package com.starking.cerveja.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starking.cerveja.controllers.page.PageWrapper;
import com.starking.cerveja.exception.EmailJaCadastradoException;
import com.starking.cerveja.exception.SenhaObrigatoriaException;
import com.starking.cerveja.model.Usuario;
import com.starking.cerveja.model.enums.StatusUsuario;
import com.starking.cerveja.repositories.GrupoRepository;
import com.starking.cerveja.repositories.UsuarioRepository;
import com.starking.cerveja.repositories.filter.UsuarioFilter;
import com.starking.cerveja.services.CadastrarUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private CadastrarUsuarioService usuarioService;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping("novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", this.grupoRepository.findAll());
		return mv;
	}
	
	@PostMapping({ "/novo", "{\\+d}" })
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
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, @PageableDefault(size =3) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("usuario/PesquisaUsuario");
		mv.addObject("grupos", this.grupoRepository.findAll());
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(this.usuarioRepository.filtrar(usuarioFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@PutMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") StatusUsuario statusUsuario) {
		this.usuarioService.alterarStatus(codigos, statusUsuario);
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Usuario usuario = this.usuarioRepository.buscarComGrupos(codigo);
		ModelAndView mv = novo(usuario);
		mv.addObject(usuario);
		return mv;
	}
}
