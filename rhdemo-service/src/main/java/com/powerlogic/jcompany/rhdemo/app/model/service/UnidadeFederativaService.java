package com.powerlogic.jcompany.rhdemo.app.model.service;

import javax.ejb.Local;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.model.service.PlcEntityService;
import com.powerlogic.jcompany.rhdemo.app.model.dto.UnidadeFederativaDTO;
import com.powerlogic.jcompany.rhdemo.app.model.entity.UnidadeFederativaEntity;

@Local
public interface UnidadeFederativaService extends PlcEntityService <Long, UnidadeFederativaEntity> {
   
	UnidadeFederativaEntity findByNome(String nome) throws PlcException;

	UnidadeFederativaDTO mudaPermissoes(UnidadeFederativaDTO usuario) throws PlcException;

	UnidadeFederativaDTO salvar(UnidadeFederativaDTO uf);

	boolean remover(UnidadeFederativaDTO uf);
}
