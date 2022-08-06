package com.starking.cerveja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.starking.cerveja.exception.EmailJaCadastradoException;
import com.starking.cerveja.exception.SenhaObrigatoriaException;
import com.starking.cerveja.model.Usuario;
import com.starking.cerveja.repositories.UsuarioRepository;

@Service
public class CadastrarUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> emailExistente = this.usuarioRepository.findByEmail(usuario.getEmail());
		if(emailExistente.isPresent()) {
			throw new EmailJaCadastradoException("Email já cadastrado");
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaException("Senha é obrigatória para novo Usuário");
		}
		
		if(usuario.isNovo()) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmaSenha(usuario.getSenha());
		}
		this.usuarioRepository.save(usuario);
	}

}
