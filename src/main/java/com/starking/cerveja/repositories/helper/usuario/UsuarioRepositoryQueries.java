package com.starking.cerveja.repositories.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.starking.cerveja.model.Usuario;

public interface UsuarioRepositoryQueries {

	Optional<Usuario> porEmailEAtivo(String email);
	
	List<String> permissoes(Usuario usuario);
}
