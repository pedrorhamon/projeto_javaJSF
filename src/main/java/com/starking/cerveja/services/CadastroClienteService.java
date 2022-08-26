package com.starking.cerveja.services;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.exception.CpfClienteJaCadastradoException;
import com.starking.cerveja.exception.ImpossivelExcluirEntidadeException;
import com.starking.cerveja.model.Cliente;
import com.starking.cerveja.repositories.ClienteRepository;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Cliente> clienteExistente = this.clienteRepository.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());
		if(clienteExistente.isPresent()) {
			throw new CpfClienteJaCadastradoException("CPF/CNPJ já cadastrado"); 
		}
		this.clienteRepository.save(cliente);
	}

	public void excluir(Cliente cliente) {
		try {
			this.clienteRepository.delete(cliente);
			this.clienteRepository.flush();
		}catch(PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cliente. Já está atrelado a alguma cerveja.");
		}
		
	}

}
