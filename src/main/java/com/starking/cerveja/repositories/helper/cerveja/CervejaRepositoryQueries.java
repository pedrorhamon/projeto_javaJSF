package com.starking.cerveja.repositories.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.model.dto.CervejaDTO;
import com.starking.cerveja.repositories.filter.CervejaFilter;

public interface CervejaRepositoryQueries {

	Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);	
	
	List<CervejaDTO> porSkuOuNome(String skuOuNome);
}
