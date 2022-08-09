package com.starking.cerveja.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.starking.cerveja.model.Usuario;
import com.starking.cerveja.repositories.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = this.usuarioRepository.porEmailEAtivo(email);
		
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		
		return new User(usuario.getEmail(), usuario.getSenha(), getPermissao(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissao(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
//		List<String> permissoes = this.usuarioRepository.permissoes(usuario);
		this.usuarioRepository.permissoes(usuario).forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
		return authorities;
	}

}
