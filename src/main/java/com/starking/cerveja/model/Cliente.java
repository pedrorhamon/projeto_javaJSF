package com.starking.cerveja.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class Cliente {
	
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@CNPJ(message = "Cnpj incorreto")
	@CPF(message = "CPF incorreto")
	@NotBlank(message = "CPF/Cnpj é obrigatório")
	private String cpfecnpj;
	
	@NotBlank(message = "Telefone é obrigatório")
	private String telefone;
	
	@NotBlank(message = "logradouro é obrigatório")
	private String logradouro;
	
	@NotBlank(message = "Número é obrigatório")
	private String numero;
	
	@NotBlank(message = "Complemento é obrigatório")
	private String complemento;
	
	private Cerveja cerveja;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfecnpj() {
		return cpfecnpj;
	}

	public void setCpfecnpj(String cpfecnpj) {
		this.cpfecnpj = cpfecnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
