package com.starking.cerveja.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.cerveja.model.Cidade;
import com.starking.cerveja.model.Estado;
import com.starking.cerveja.repositories.helper.cidade.CidadeRepositoryQueries;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryQueries{
	
	List<Cidade> findByEstadoCodigo(Long codigoEstado);
	
	Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
}
