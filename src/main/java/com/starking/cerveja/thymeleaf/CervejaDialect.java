package com.starking.cerveja.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.starking.cerveja.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.starking.cerveja.thymeleaf.processor.MessageElementTagProcessor;

public class CervejaDialect extends AbstractProcessorDialect {

	public CervejaDialect() {
		super("Starking Cerveja", "cerveja", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
