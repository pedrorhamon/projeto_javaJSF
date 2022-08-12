package com.starking.cerveja.config.init;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		servletContext.getSessionCookieConfig().setMaxAge(20);
		
		FilterRegistration chFilterRegistrationFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
		chFilterRegistrationFilter.setInitParameter("encoding", "UTF-8");
		chFilterRegistrationFilter.setInitParameter("forceEncoding", "true");
		chFilterRegistrationFilter.addMappingForServletNames(null, false, "/*");
	}

}
