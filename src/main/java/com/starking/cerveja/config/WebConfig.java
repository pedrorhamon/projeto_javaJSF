package com.starking.cerveja.config;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.starking.cerveja.controllers.CervejaController;
import com.starking.cerveja.controllers.converters.EstiloConverter;
import com.starking.cerveja.thymeleaf.CervejaDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackageClasses = {CervejaController.class})
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@Bean
	public TemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
		
		engine.addDialect(new LayoutDialect());
		engine.addDialect(new CervejaDialect());
		return engine;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}
	
	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix("classpath:/templates/");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setSuffix(".html");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService cs = new DefaultFormattingConversionService();
		cs.addConverter(new EstiloConverter());	
		
		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
		cs.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);
		
		NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
		cs.addFormatterForFieldType(Integer.class, integerFormatter);
		
		return cs;
	}
	
	@Bean
	public LocaleResolver localResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
