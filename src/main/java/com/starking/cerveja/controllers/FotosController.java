package com.starking.cerveja.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.starking.cerveja.storage.FotoStorageRunnable;

@RestController
@RequestMapping("fotos")
public class FotosController {

	@PostMapping
	public DeferredResult<String> upload(MultipartFile[] files) {
		DeferredResult<String> resultado = new DeferredResult<>();
		Thread thread = new Thread(new FotoStorageRunnable(files,resultado));
		
		thread.start();
		return resultado;
	}
}
