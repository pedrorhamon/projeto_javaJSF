package com.starking.cerveja.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.starking.cerveja.model.Usuario;

public class UsuarioSystem extends User{
	
	private Usuario usuario;

	public UsuarioSystem(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	private static final long serialVersionUID = 1L;
	
	public Usuario getUsuario() {
		return usuario;
	}
}
