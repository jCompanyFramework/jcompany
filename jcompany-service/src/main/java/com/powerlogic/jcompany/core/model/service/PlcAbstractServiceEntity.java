package com.powerlogic.jcompany.core.model.service;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.commons.search.PlcPagedResult;
import com.powerlogic.jcompany.core.commons.search.PlcPagination;
import com.powerlogic.jcompany.core.exception.PlcBeanMessages;
import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;
import com.powerlogic.jcompany.core.model.repository.PlcEntityRepository;

public abstract class PlcAbstractServiceEntity <PK extends Serializable, E extends PlcVersionedEntity<PK>> implements PlcEntityService<PK, E> {

	protected abstract PlcEntityRepository<PK, E> getEntityRepository();

	@Override
	public E newEntity() {
		try {
			return getEntityRepository().getEntityType().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException("Construtor padrão não existente...");
		}
	}

	@Override
	public List<E> findAll() throws PlcException {
		return getEntityRepository().findAll();
	}
	
	@Override
	public List<E> findAll(E entity) throws PlcException {
		return getEntityRepository().findAll(entity);
	}	

	@Override
	public PlcPagedResult<E> find(PlcPagination<E> config) throws PlcException {
		return getEntityRepository().find(config);
	}

	@Override
	public E get(PK id) {
		return getEntityRepository().get(id);
	}

	@Override
	public E save(@Valid E entity) throws PlcException {
		return getEntityRepository().save(entity);
	}

	@Override
	public void remove(E entity) throws PlcException {
		try {
			E novo = get(entity.getId());
			novo.setUsuarioAtualizacao(entity.getUsuarioAtualizacao());
			novo.setVersao(entity.getVersao());
			getEntityRepository().remove(novo);
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_OPERACAO_003.create();
		}
	}

	@Override
	public void inative(E entity) throws PlcException {
		try {
			E novo = get(entity.getId());
			novo.setUsuarioAtualizacao(entity.getUsuarioAtualizacao());
			novo.setVersao(entity.getVersao());
			getEntityRepository().inative(novo);
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_OPERACAO_003.create();
		}
	}
}
