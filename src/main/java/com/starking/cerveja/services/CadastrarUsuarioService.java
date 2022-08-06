package com.starking.cerveja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.exception.EmailJaCadastradoException;
import com.starking.cerveja.model.Usuario;
import com.starking.cerveja.repositories.UsuarioRepository;

@Service
public class CadastrarUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> emailExistente = this.usuarioRepository.findByEmail(usuario.getEmail());
		if(emailExistente.isPresent()) {
			throw new EmailJaCadastradoException("Email já cadastrado");
		}
		this.usuarioRepository.save(usuario);
	}

}
