package com.starking.cerveja.storage.copy;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage  {

	String salvarTemporariamente(MultipartFile[] files);

	byte[] recuperarFotoTemporaria(String nome);

	void salvar(String foto);

	public byte[] recuperar(String nome);
	
}
