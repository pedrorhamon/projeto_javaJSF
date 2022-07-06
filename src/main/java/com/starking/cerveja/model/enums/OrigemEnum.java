package com.starking.cerveja.model.enums;

public enum OrigemEnum {
	
	NACIONAL("Nacional"), 
	INTERNACIONAL("Internacional");
	
	private String descricao;
	
	OrigemEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
