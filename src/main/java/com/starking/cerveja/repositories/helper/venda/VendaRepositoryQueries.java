package com.starking.cerveja.repositories.helper.venda;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starking.cerveja.model.Venda;
import com.starking.cerveja.repositories.filter.VendaFilter;

public interface VendaRepositoryQueries {

	Page<Venda> filtrar(VendaFilter filtro, Pageable pageable);

	Venda buscarComItens(Long codigo);

	BigDecimal valorTotalNoAno();

	BigDecimal valorTotalNoMes();

	BigDecimal valorTicketMedioNoAno();
}
