/*  																													
	    			       Jaguar-jCompany Developer Suite.																		
			    		        Powerlogic 2010-2014.
			    		    
		Please read licensing information in your installation directory.Contact Powerlogic for more 
		information or contribute with this project: suporte@powerlogic.com.br - www.powerlogic.com.br																								
 */
package com.powerlogic.jcompany.commons.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.powerlogic.jcompany.commons.util.interpolator.AggregateResourceBundleLocator;
import com.powerlogic.jcompany.commons.util.interpolator.ResourceBundleMessageInterpolator;

/**
 * Serviço dinâmicos para realização de validação invariante, vinculada ao
 * modelo de domínio e podendo ser ativada em qualquer camada
 */
public class PlcValidationInvariantUtil implements Serializable {
	

	private static final long serialVersionUID = 1L;

	/**
	 * Realiza validação invariante no padrão de Bean Validator
	 * 
	 * @param vo
	 * @param groups
	 * @return
	 */
	public <T> Set<ConstraintViolation<T>> invariantValidation(T vo, Class<?>... groups) {
		Validator validator = Validation.byDefaultProvider()
		        .configure()
		        .messageInterpolator(
		                new ResourceBundleMessageInterpolator(
		                        new AggregateResourceBundleLocator(
		                                Arrays.asList(
		                                        "PlcMessages",
		                                        "AppMessages"
		                                )
		                        )
		                )
		        )
		        .buildValidatorFactory()
		        .getValidator();
		
		Set<ConstraintViolation<T>> ivs = null;
		// Valida efetivamente
		if (vo != null) {
			ivs = validator.validate(vo, groups);
		}	

		return ivs;

	}


	
}
