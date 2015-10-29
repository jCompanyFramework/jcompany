package com.powerlogic.jcompany.rhdemo.app.model.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.exception.PlcBeanMessages;
import com.powerlogic.jcompany.core.model.domain.PlcSituacao;
import com.powerlogic.jcompany.core.model.repository.PlcAbstractRepository;
import com.powerlogic.jcompany.core.util.ConstantUtil;
import com.powerlogic.jcompany.rhdemo.app.model.entity.DepartamentoEntity;

@ApplicationScoped
public class DepartamentoRepository extends PlcAbstractRepository<Long, DepartamentoEntity> {
	
	public DepartamentoEntity save(DepartamentoEntity entity) throws PlcException {
		return super.save(entity);
	}

	@Override
	public Class<DepartamentoEntity> getEntityType() {
		return DepartamentoEntity.class;
	}

	@Override
	public List<DepartamentoEntity> findAll(DepartamentoEntity departamento) {

		try {
			
			CriteriaBuilder builder = criteriaBuilder();

			CriteriaQuery<DepartamentoEntity> query = builder.createQuery(getEntityType());

			Root<DepartamentoEntity> from = query.from(getEntityType());

			query.select(from).where(builder.equal(from.get(ConstantUtil.QUERY_PARAM_SITUACAO), PlcSituacao.A));
			
			//TODO: Colocar generico no framework...
			List<Predicate> predicates= new ArrayList<Predicate>();
			
			if(StringUtils.isNoneBlank(departamento.getDescricao())) {
			    Predicate nome = builder.like(from.<String>get("descricao"), departamento.getDescricao());
			    predicates.add(nome);
			}
			
			if(departamento.getDepartamentoPai() != null) {
			    Predicate departamentoPai = builder.equal(from.<DepartamentoEntity>get("departamentoPai"), departamento.getDepartamentoPai());
			    predicates.add(departamentoPai);
			}
			 
			query.where(predicates.toArray(new Predicate[]{}));
			
			return createQuery(query).getResultList();
			
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
		
	}

	
	public DepartamentoEntity findByRoot(Long id) {
		//Buscar pelo Pai
		return null;
	}
	
	@Override
	public List<DepartamentoEntity> findAll() throws PlcException {
		//throw AppErrorMessage.TESTE_MESSAGE_ERROR_APP_01.create();
		return super.findAll();
	}

}