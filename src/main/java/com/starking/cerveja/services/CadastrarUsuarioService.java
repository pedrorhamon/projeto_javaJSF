package com.starking.cerveja.services;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.starking.cerveja.exception.EmailUsuarioJaCadastradoException;
import com.starking.cerveja.exception.ImpossivelExcluirEntidadeException;
import com.starking.cerveja.exception.SenhaObrigatoriaException;
import com.starking.cerveja.model.Usuario;
import com.starking.cerveja.model.enums.StatusUsuario;
import com.starking.cerveja.repositories.UsuarioRepository;

@Service
public class CadastrarUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = this.usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaException("Senha é obrigatória para novo Usuário");
		}
		
		if(usuario.isNovo()|| !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		}else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmaSenha(usuario.getSenha());
		
		if (!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		this.usuarioRepository.save(usuario);
	}

	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario status) {
		status.executar(codigos, usuarioRepository);
	}

	@Transactional
	public void excluir(Usuario usuario) {
		try {
			this.usuarioRepository.delete(usuario);
			this.usuarioRepository.flush();
		}catch(PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel excluir usuario está atrelado ao sistema");
		}
	}
}
