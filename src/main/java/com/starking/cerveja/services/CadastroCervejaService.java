package com.starking.cerveja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.repositories.CervejaRepository;

@Service
public class CadastroCervejaService {

	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		this.cervejaRepository.save(cerveja);
	}
}
