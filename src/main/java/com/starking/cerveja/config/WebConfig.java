package com.starking.cerveja.config;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;
import com.google.common.cache.CacheBuilder;
import com.starking.cerveja.config.format.BigDecimalFormatter;
import com.starking.cerveja.controllers.CervejaController;
import com.starking.cerveja.controllers.converters.CidadeConverter;
import com.starking.cerveja.controllers.converters.EstadoConverter;
import com.starking.cerveja.controllers.converters.EstiloConverter;
import com.starking.cerveja.controllers.converters.GrupoConverter;
import com.starking.cerveja.session.TabelaItensSession;
import com.starking.cerveja.thymeleaf.CervejaDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackageClasses = {CervejaController.class, TabelaItensSession.class})
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableCaching
@EnableAsync
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
		engine.addDialect(new DataAttributeDialect());
		engine.addDialect(new SpringSecurityDialect());
		return engine;
	}
	
	@Bean
	public ViewResolver jasperReportsViewResolver(DataSource datasource) {
		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix("classpath:/relatorios/");
		resolver.setSuffix(".jasper");
		resolver.setViewNames("relatorio_*");
		resolver.setViewClass(JasperReportsMultiFormatView.class);
		resolver.setJdbcDataSource(datasource);
		resolver.setOrder(0);
		return resolver;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		resolver.setOrder(1);
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
		cs.addConverter(new CidadeConverter());
		cs.addConverter(new EstadoConverter());
		cs.addConverter(new GrupoConverter());
		
//		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
		BigDecimalFormatter bigDecimalFormatter = new BigDecimalFormatter("#,##0.00");
		cs.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);
		
//		NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
		BigDecimalFormatter integerFormatter = new BigDecimalFormatter("#,##0");
		cs.addFormatterForFieldType(Integer.class, integerFormatter);
		
		DateTimeFormatterRegistrar formatterRegistrar = new DateTimeFormatterRegistrar();
		formatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		formatterRegistrar.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm"));
		formatterRegistrar.registerFormatters(cs);
		
		return cs;
	}
	
	@Bean
	public LocaleResolver localResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
				.maximumSize(3)
				.expireAfterAccess(20, TimeUnit.SECONDS);
		
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("classpath:/messages");
		bundle.setDefaultEncoding("UTF-8");
		return bundle;
	}
	
	@Bean
	public DomainClassConverter<FormattingConversionService> domainClassConverter() {
		return new DomainClassConverter<FormattingConversionService>(mvcConversionService());
	}
}
