package com.starking.cerveja.controllers.converters;

import org.springframework.core.convert.converter.Converter;

import com.starking.cerveja.model.Estilo;

public class EstiloConverter implements Converter<String, Estilo>{

	@Override
	public Estilo convert(String id) {
		Estilo estilo = new Estilo();
		estilo.setId(Long.valueOf(id));
		return estilo;
	}

}
