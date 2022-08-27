package com.starking.cerveja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.cerveja.model.Venda;
import com.starking.cerveja.repositories.helper.venda.VendaRepositoryQueries;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>, VendaRepositoryQueries{

}
