package com.starking.cerveja.repositories.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starking.cerveja.model.Cidade;
import com.starking.cerveja.repositories.filter.CidadeFilter;

public interface CidadeRepositoryQueries {
	
	Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable);
}
