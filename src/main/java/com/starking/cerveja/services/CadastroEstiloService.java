package com.starking.cerveja.services;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.exception.ImpossivelExcluirEntidadeException;
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
	
	@Transactional
	public void excluir(Estilo estilo) {
		try {
			this.estiloRepository.delete(estilo);
			this.estiloRepository.flush();
		}catch(PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar estilo. Já está atrelado a alguma cerveja.");
		}
	}

}
