package com.powerlogic.jcompany.core.rest.entity;

import java.io.Serializable;
import java.util.List;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.commons.search.PlcPagedResult;
import com.powerlogic.jcompany.core.model.entity.PlcEntityModel;
import com.powerlogic.jcompany.core.model.service.PlcEntityService;
import com.powerlogic.jcompany.core.rest.PlcAbstractRest;

public abstract class PlcAbstractEntityRest <PK extends Serializable, E extends PlcEntityModel<PK>, A>
		extends PlcAbstractRest implements PlcEntityModelRest<PK, E, A> {

	protected abstract PlcEntityService<PK, E> getEntityService();

	protected List<E> findAll() throws PlcException {
		return getEntityService().findAll();
	}

	@Override
	public E newEntity() throws PlcException {
		return getEntityService().newEntity();
	}

	@Override
	public E get(PK entityId) throws PlcException {
		return getEntityService().get(entityId);
	}

	@Override
	public E save(E entity) throws PlcException {
		return getEntityService().save(entity);
	}

	@Override
	public boolean remove(E entity) throws PlcException {
		getEntityService().remove(entity);
		return true;
	}

	@Override
	public PlcPagedResult<E> find(A searchBuilder) throws PlcException {
		throw new UnsupportedOperationException();
	}
}