package com.starking.cerveja.repositories.helper.estilo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starking.cerveja.model.Estilo;
import com.starking.cerveja.repositories.filter.EstiloFilter;

public interface EstiloRepositoryQueries {
	
	Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);	

}
