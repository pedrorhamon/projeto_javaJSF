package com.starking.cerveja.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.starking.cerveja.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.starking.cerveja.thymeleaf.processor.MenuAttributeTagProcessor;
import com.starking.cerveja.thymeleaf.processor.MessageElementTagProcessor;
import com.starking.cerveja.thymeleaf.processor.OrderElementTagProcessor;
import com.starking.cerveja.thymeleaf.processor.PaginationElementTagProcessor;

public class CervejaDialect extends AbstractProcessorDialect {

	public CervejaDialect() {
		super("Starking Cerveja", "cerveja", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
