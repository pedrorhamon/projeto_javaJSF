package com.starking.cerveja.services;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.exception.ImpossivelExcluirEntidadeException;
import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.repositories.CervejaRepository;
import com.starking.cerveja.services.event.cerveja.CervejaSalvaEvent;
import com.starking.cerveja.storage.FotoStorage;

@Service
public class CadastroCervejaService {

	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		this.cervejaRepository.save(cerveja);
		
		this.publisher.publishEvent(new CervejaSalvaEvent(cerveja));
	}
	
	@Transactional
	public void excluir(Cerveja cerveja) {
		try {
			String foto = cerveja.getFoto();
			this.cervejaRepository.delete(cerveja);
			this.cervejaRepository.flush();
			fotoStorage.excluir(foto);
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cerveja. Já foi usada em alguma venda.");
		}
	}
}
