package com.starking.cerveja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starking.cerveja.model.Cidade;
import com.starking.cerveja.repositories.CidadeRepository;



@Controller
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@RequestMapping("/novo")
	public String novo() {
		return "cidade/CadastroCidade";
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(@RequestParam(name ="estado", defaultValue = "-1" ) Long estadoId) {
		return this.cidadeRepository.findByEstadoCodigo(estadoId);
	}
	
	@RequestMapping
	public String pesquisar() {
		return "cidade/CadastroCidade";
	}
	
}
