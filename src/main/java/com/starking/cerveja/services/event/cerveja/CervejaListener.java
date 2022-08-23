package com.starking.cerveja.services.event.cerveja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.starking.cerveja.storage.FotoStorage;

@Component
public class CervejaListener {

	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto() and #evento.novaFoto()")
	public void cervejaSalva(CervejaSalvaEvent evento) {
		this.fotoStorage.salvar(evento.getCerveja().getFoto());
	}
}
