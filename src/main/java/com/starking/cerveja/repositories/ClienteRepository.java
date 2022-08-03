package com.starking.cerveja.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.cerveja.model.Cliente;
import com.starking.cerveja.repositories.helper.cliente.ClienteRepositoryQueries;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQueries{
	
	Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
