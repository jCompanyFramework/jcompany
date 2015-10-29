package com.powerlogic.jcompany.rhdemo.app.model.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.powerlogic.jcompany.core.model.repository.PlcEntityRepository;
import com.powerlogic.jcompany.core.model.service.PlcAbstractServiceEntity;
import com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario.FuncionarioEntity;
import com.powerlogic.jcompany.rhdemo.app.model.repository.FuncionarioRepository;

@Stateless
public class FuncionarioServiceImpl extends PlcAbstractServiceEntity<Long, FuncionarioEntity> implements FuncionarioService {
	
	@Inject
	private FuncionarioRepository funcionarioRepository;


	@Override
	protected PlcEntityRepository<Long, FuncionarioEntity> getEntityRepository() {
		return funcionarioRepository;
	}
}
