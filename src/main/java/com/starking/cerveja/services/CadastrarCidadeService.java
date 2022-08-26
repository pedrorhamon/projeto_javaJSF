package com.starking.cerveja.services;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.exception.EstadoCidadeJaCadastradoException;
import com.starking.cerveja.exception.ImpossivelExcluirEntidadeException;
import com.starking.cerveja.model.Cidade;
import com.starking.cerveja.repositories.CidadeRepository;

@Service
public class CadastrarCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = this.cidadeRepository.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if(cidadeExistente.isPresent()) {
			throw new EstadoCidadeJaCadastradoException("Cidade já cadastrado"); 
		}
		this.cidadeRepository.save(cidade);
	}

	@Transactional
	public void excluir(Cidade cidade) {
		try {
			this.cidadeRepository.delete(cidade);
			this.cidadeRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cidade. O registro está sendo usado.");
		}
	}

}
