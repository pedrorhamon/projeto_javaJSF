package com.starking.cerveja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.repositories.CervejaRepository;
import com.starking.cerveja.services.event.cerveja.CervejaSalvaEvent;

@Service
public class CadastroCervejaService {

	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		this.cervejaRepository.save(cerveja);
		
		this.publisher.publishEvent(new CervejaSalvaEvent(cerveja));
	}
}
