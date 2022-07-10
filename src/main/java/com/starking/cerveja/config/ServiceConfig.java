package com.starking.cerveja.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.starking.cerveja.services")
public class ServiceConfig {

}
