package com.starking.cerveja.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.cerveja.model.Usuario;
import com.starking.cerveja.repositories.helper.usuario.UsuarioRepositoryQueries;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueries{
	
	Optional<Usuario> findByEmail(String email);
	
	List<Usuario> findByCodigoIn(Long[] codigos);
}
