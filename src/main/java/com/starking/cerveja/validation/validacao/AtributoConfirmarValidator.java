package com.starking.cerveja.validation.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.starking.cerveja.validation.AtributoConfirmacao;

public class AtributoConfirmarValidator implements ConstraintValidator<AtributoConfirmacao, Object>  {

	private String atributo;
	private String atributoConfirmacao;
	
	@Override
	public void initialize(AtributoConfirmacao constraintAnnotation) {
		this.atributo = constraintAnnotation.atributo();
		this.atributoConfirmacao = constraintAnnotation.atributoConfirmacao();
	}

	@SuppressWarnings("unused")
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean isValido = false;
		
		try {			
			Object valorAtributo = BeanUtils.getProperty(value, this.atributo);
			Object valorAtributoConfirmacao = BeanUtils.getProperty(value, this.atributoConfirmacao);
			
			isValido = ambosSaoNull(valorAtributo, valorAtributoConfirmacao) || ambosSaoIguais(valorAtributo, valorAtributoConfirmacao);
			
		} catch(Exception e) {
			throw new RuntimeException("Erro recuperando valores dos atributos", e);
		}
		
		if(!isValido) {
			context.disableDefaultConstraintViolation();
			String msg = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(msg);
			violationBuilder.addPropertyNode(atributoConfirmacao).addConstraintViolation();
		}

		return isValido;
	}

	private boolean ambosSaoIguais(Object valorAtributo, Object valorAtributoConfirmacao) {
		return  valorAtributo != null && valorAtributo.equals(valorAtributoConfirmacao);
	}

	private boolean ambosSaoNull(Object valorAtributo, Object valorAtributoConfirmacao) {
		return valorAtributo == null && valorAtributoConfirmacao == null;
	}

}
