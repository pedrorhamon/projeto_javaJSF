package com.starking.cerveja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.exception.NomeEstiloException;
import com.starking.cerveja.model.Estilo;
import com.starking.cerveja.repositories.EstiloRepository;

@Service
public class CadastroEstiloService {
	
	@Autowired
	private EstiloRepository estiloRepository;
	
	@Transactional
	public Estilo salvar(Estilo estilo) {
		Optional<Estilo> estiloOptional = this.estiloRepository.findByNomeIgnoreCase(estilo.getNome());
		if (estiloOptional.isPresent()) {
			throw new NomeEstiloException("Nome do estilo já cadastrado");
		}
		
		return this.estiloRepository.saveAndFlush(estilo);
	}

}
