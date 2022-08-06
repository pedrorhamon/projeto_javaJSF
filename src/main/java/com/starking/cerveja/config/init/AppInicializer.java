package com.starking.cerveja.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.starking.cerveja.config.JPAConfig;
import com.starking.cerveja.config.SecurityConfig;
import com.starking.cerveja.config.ServiceConfig;
import com.starking.cerveja.config.WebConfig;

public class AppInicializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {JPAConfig.class, ServiceConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter charFilter = new CharacterEncodingFilter();
		charFilter.setEncoding("UTF-8");
		charFilter.setForceEncoding(true);
		
		return new Filter[] {charFilter};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
