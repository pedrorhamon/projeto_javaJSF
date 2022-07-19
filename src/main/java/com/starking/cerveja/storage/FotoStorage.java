package com.starking.cerveja.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage  {

	String salvarTemporariamente(MultipartFile[] files);

	byte[] recuperarFotoTemporaria(String nome);

	void salvar(String foto);
	
}
