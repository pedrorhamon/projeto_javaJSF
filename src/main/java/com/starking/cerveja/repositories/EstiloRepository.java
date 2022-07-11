package com.starking.cerveja.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.cerveja.model.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Long>{
	
	Optional<Estilo> findByNomeIgnoreCase(String nome);

}
