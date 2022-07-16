package com.starking.cerveja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.starking.cerveja.storage.FotoStorage;
import com.starking.cerveja.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackages = "com.starking.cerveja.services")
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
}
