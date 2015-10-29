package com.powerlogic.jcompany.commons.validation;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import com.powerlogic.jcompany.commons.util.PlcMessageUtil;
import com.powerlogic.jcompany.commons.util.PlcValidationInvariantUtil;
import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.exception.PlcBeanMessages;

public class PlcValidationInterceptor  {

	@Inject
	private PlcValidationInvariantUtil validationUtil;
	
	@Inject
	private PlcMessageUtil messageUtil;
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		
		Annotation[][] annotations = context.getMethod().getParameterAnnotations();
		
		for(int i=0; i < annotations.length; i++ ){
			for(Annotation annotation: annotations[i]) {
				if(annotation.annotationType().equals(Valid.class)){
					checkBeanValidation(context.getParameters()[i]);
				}
			}
		}
		
		Object result = context.proceed();
		return result;
	}

	/**
	 * Método para a validação invariante do formulário na gravação simples.
	 * @return true se tudo estiver válido.
	 */
	public void checkBeanValidation(Object entityPlc, Class<?>... groups)  {

	 	// Validação invariante padrão Hibernate Validator.
		Set<ConstraintViolation<Object>> cv = null;
		
		if (entityPlc!=null) {
			try {
				cv = validationUtil.invariantValidation(entityPlc, groups);
			}catch(PlcException ePlc){
				throw ePlc;
			} catch (Exception e) {
				throw PlcBeanMessages.FALHA_OPERACAO_003.create();
			}	
		}	
		boolean validacaoInvarianteOk = (cv == null || cv.isEmpty());

		//invocando a validação
		if (!validacaoInvarianteOk) {
			throw PlcBeanMessages.FALHA_VALIDACAO_023.create(messageUtil.availableInvariantMessages(cv));
		}
		
	}
}
