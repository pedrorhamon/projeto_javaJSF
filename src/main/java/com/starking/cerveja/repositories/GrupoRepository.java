package com.starking.cerveja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.cerveja.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{

}
