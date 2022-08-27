package com.starking.cerveja.repositories.helper.venda;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starking.cerveja.model.Venda;
import com.starking.cerveja.model.VendaMes;
import com.starking.cerveja.repositories.filter.VendaFilter;

public interface VendaRepositoryQueries {

	Page<Venda> filtrar(VendaFilter filtro, Pageable pageable);

	Venda buscarComItens(Long codigo);

	BigDecimal valorTotalNoAno();

	BigDecimal valorTotalNoMes();

	BigDecimal valorTicketMedioNoAno();
	
	List<VendaMes> totalPorMes();
}
