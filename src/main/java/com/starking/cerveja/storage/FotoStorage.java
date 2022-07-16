package com.starking.cerveja.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage  {

	void salvarTemporariamente(MultipartFile[] files);
	
}
