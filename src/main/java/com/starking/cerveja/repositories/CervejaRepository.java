package com.starking.cerveja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.repositories.helper.cerveja.CervejaRepositoryQueries;

@Repository
public interface CervejaRepository extends JpaRepository<Cerveja, Long>, CervejaRepositoryQueries{
	
//	Optional<Cerveja> findBySkuIgnoreCase(String sku);

//	Cerveja findOne(Long codigoCerveja);
}
