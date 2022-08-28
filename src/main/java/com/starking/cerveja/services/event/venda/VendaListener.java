package com.starking.cerveja.services.event.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import com.starking.cerveja.model.Cerveja;
import com.starking.cerveja.model.ItemVenda;
import com.starking.cerveja.repositories.CervejaRepository;

public class VendaListener {

	@Autowired
	private CervejaRepository cervejaRepository;
	
	@EventListener
	public void vendaEmitida(VendaEvent vendaEvent) {
		for (ItemVenda item : vendaEvent.getVenda().getItens()) {
			Cerveja cerveja = this.cervejaRepository.findOne(item.getCerveja().getId());
			cerveja.setQuantidadeEstoque(cerveja.getQuantidadeEstoque() - item.getQuantidade());
			this.cervejaRepository.save(cerveja);
		}
	}
}
