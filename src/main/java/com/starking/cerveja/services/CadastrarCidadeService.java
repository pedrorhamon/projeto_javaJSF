package com.starking.cerveja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.exception.EstadoCidadeJaCadastradoException;
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

}
