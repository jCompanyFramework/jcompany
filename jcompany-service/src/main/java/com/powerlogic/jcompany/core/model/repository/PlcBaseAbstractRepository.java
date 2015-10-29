package com.powerlogic.jcompany.core.model.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.commons.search.PlcPagedResult;
import com.powerlogic.jcompany.core.commons.search.PlcPagination;
import com.powerlogic.jcompany.core.exception.PlcBeanMessages;
import com.powerlogic.jcompany.core.model.domain.PlcSituacao;
import com.powerlogic.jcompany.core.model.entity.PlcLogicalExclusion;
import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;
import com.powerlogic.jcompany.core.util.ConstantUtil;

public abstract class PlcBaseAbstractRepository<PK extends Serializable, E extends PlcVersionedEntity<PK>> implements PlcEntityRepository<PK, E> {

	protected abstract EntityManager getEntityManager();

	public abstract Class<E> getEntityType();

	@Override
	public E get(PK id) {
		
		try {
			return getEntityManager().find(getEntityType(), id);
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	@Override
	public E getDetached(PK id) {
		
		try {
			E ent = getEntityManager().find(getEntityType(), id);
			getEntityManager().detach(ent);
			return ent;
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	@Override
	public E save(E entity) {
		// Implementar Listener JPA
		try {

			if (entity.getId() == null) {
				getEntityManager().persist(entity);
			} else {
				entity = getEntityManager().merge(entity);
			}
		} catch (OptimisticLockException e) {
			throw PlcBeanMessages.REGISTROS_CONCORRENTES_011.create();
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}

		return entity;
	}

	@Override
	public void remove(E entity) {
		try {
			if (entity instanceof PlcLogicalExclusion) {
				logicalRemove(entity);
			} else {
				physicalRemove(entity);
			}
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	protected void logicalRemove(E entity) {
		
		try {
			entity.setSituacao(PlcSituacao.E);
			getEntityManager().merge(entity);
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	protected void physicalRemove(E entity) {
		try {
			getEntityManager().remove(entity);
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	@Override
	public void inative(E entity) {
		
		try {
			
			entity.setSituacao(PlcSituacao.I);
			getEntityManager().merge(entity);
			
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	@Override
	public List<E> findAll() {
		try {
			CriteriaBuilder builder = criteriaBuilder();

			CriteriaQuery<E> query = builder.createQuery(getEntityType());

			Root<E> from = query.from(getEntityType());

			query.select(from).where(builder.equal(from.get(ConstantUtil.QUERY_PARAM_SITUACAO), PlcSituacao.A));

			return createQuery(query).getResultList();
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	@Override
	public PlcPagedResult<E> find(PlcPagination<E> config) {
		
		try {
			CriteriaQuery<E> query = criteriaBuilder().createQuery(getEntityType());

			query.select(query.from(getEntityType()));

			return getPagedResult(config, createQuery(query));

		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	@Override
	public List<E> findAll(E entity) throws PlcException {

		try {
			
			CriteriaBuilder builder = criteriaBuilder();

			CriteriaQuery<E> query = builder.createQuery(getEntityType());

			Root<E> from = query.from(getEntityType());

			query.select(from).where(builder.equal(from.get(ConstantUtil.QUERY_PARAM_SITUACAO), PlcSituacao.A));

			return createQuery(query).getResultList();
			
		} catch( PlcException e){
			throw e;
		} catch( Exception e){ 
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	protected <T> PlcPagedResult<T> getPagedResult(PlcPagination<T> config, TypedQuery<T> query) {
		
		try {
		
			query.setFirstResult(config.getOffset());
			query.setMaxResults(config.getLimit());

			return new PlcPagedResult<>(config, config.getLimit(), query.getResultList());
			
		} catch( PlcException e){
			throw e;
		} catch( Exception e){ 
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

	protected CriteriaBuilder criteriaBuilder() {
		
		try {
			return getEntityManager().getCriteriaBuilder();
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
		
	}

	protected <T> TypedQuery<T> createQuery(CriteriaQuery<T> query) {
		
		try {
			return getEntityManager().createQuery(query);
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
	}

}