package com.powerlogic.jcompany.rhdemo.app.model.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.powerlogic.jcompany.commons.validation.PlcValidationInterceptor;
import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.model.repository.PlcEntityRepository;
import com.powerlogic.jcompany.core.model.service.PlcAbstractServiceEntity;
import com.powerlogic.jcompany.rhdemo.app.model.dto.UnidadeFederativaDTO;
import com.powerlogic.jcompany.rhdemo.app.model.entity.UnidadeFederativaEntity;
import com.powerlogic.jcompany.rhdemo.app.model.repository.UnidadeFederativaRepository;

@Stateless
@Interceptors ({PlcValidationInterceptor.class})
public class UnidadeFederativaServiceImpl extends PlcAbstractServiceEntity<Long, UnidadeFederativaEntity> implements UnidadeFederativaService {
	
	@Inject
	private UnidadeFederativaRepository unidadeFederativaRepository;

	@Override
	public UnidadeFederativaEntity findByNome(String nome) {
		return unidadeFederativaRepository.findByNome(nome);
	}
 
	@Override
	public UnidadeFederativaDTO mudaPermissoes(UnidadeFederativaDTO usuario) throws PlcException {
		return usuario;
	}

	@Override
	protected PlcEntityRepository<Long, UnidadeFederativaEntity> getEntityRepository() {
		return unidadeFederativaRepository;
	}

	@Override
	public UnidadeFederativaDTO salvar(UnidadeFederativaDTO uf) {
		return null;
	}

	@Override
	public boolean remover(UnidadeFederativaDTO uf) {
		return false;
	}
	
}
