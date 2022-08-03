package com.starking.cerveja.repositories.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starking.cerveja.model.Cliente;
import com.starking.cerveja.repositories.filter.ClienteFilter;

public interface ClienteRepositoryQueries {
	
	Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);

}
