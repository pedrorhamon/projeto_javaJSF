package com.starking.cerveja.model.enums;

import com.starking.cerveja.model.CnpjGroup;
import com.starking.cerveja.model.CpfGroup;

public enum TipoPessoa {
	
	FISICA("Física", "CPF", "000.000.000-00", CpfGroup.class),
	JURIDICA("Jurídica", "CNPJ", "000.000.000/0000-00", CnpjGroup.class);
	
	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> grupo;
	
	
	private TipoPessoa(String descricao, String documento, String mascara, Class<?> grupo) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.grupo = grupo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public String getMascara() {
		return mascara;
	}
	
	public Class<?> getGrupo() {
		return grupo;
	}
}
