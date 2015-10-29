package com.powerlogic.jcompany.rhdemo.app.model.service;

import javax.ejb.Local;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.model.service.PlcEntityService;
import com.powerlogic.jcompany.rhdemo.app.model.dto.DepartamentoDTO;
import com.powerlogic.jcompany.rhdemo.app.model.entity.DepartamentoEntity;

@Local
public interface DepartamentoService extends PlcEntityService<Long, DepartamentoEntity> {
   
	DepartamentoEntity findByRoot(Long id) throws PlcException;

	DepartamentoDTO salvar(DepartamentoDTO departamento);

	boolean remover(DepartamentoDTO departamento);
}
