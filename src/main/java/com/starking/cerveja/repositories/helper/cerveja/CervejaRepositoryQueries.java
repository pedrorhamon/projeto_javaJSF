package com.starking.cerveja.repositories.helper.cerveja;

import java.util.List;

import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.repositories.filter.CervejaFilter;

public interface CervejaRepositoryQueries {

	public List<Cerveja> filtrar(CervejaFilter filtro);	
}
