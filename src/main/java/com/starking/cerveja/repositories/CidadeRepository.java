package com.starking.cerveja.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.cerveja.model.Cidade;
import com.starking.cerveja.model.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	List<Cidade> findByEstadoCodigo(Long codigoEstado);
	
	Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
}
