package com.powerlogic.jcompany.rhdemo.app.model.service;

import javax.ejb.Local;

import com.powerlogic.jcompany.core.model.service.PlcEntityService;
import com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario.FuncionarioEntity;

@Local
public interface FuncionarioService extends PlcEntityService<Long, FuncionarioEntity> {
   

}
