package com.starking.cerveja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.cerveja.exception.CpfClienteJaCadastradoException;
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

}
