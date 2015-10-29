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
import com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario.FuncionarioEntity;

@ApplicationScoped
public class FuncionarioRepository extends PlcAbstractRepository<Long, FuncionarioEntity> {
	
	public FuncionarioEntity save(FuncionarioEntity entity) throws PlcException {
		return super.save(entity);
	}

	@Override
	public Class<FuncionarioEntity> getEntityType() {
		return FuncionarioEntity.class;
	}

	@Override
	public List<FuncionarioEntity> findAll(FuncionarioEntity funcionario) {
	
		try {
			
			CriteriaBuilder builder = criteriaBuilder();

			CriteriaQuery<FuncionarioEntity> query = builder.createQuery(getEntityType());

			Root<FuncionarioEntity> from = query.from(getEntityType());

			query.select(from).where(builder.equal(from.get(ConstantUtil.QUERY_PARAM_SITUACAO), PlcSituacao.A));
			
			//TODO: Colocar generico no framework...
			List<Predicate> predicates= new ArrayList<Predicate>();
			
			if(StringUtils.isNoneBlank(funcionario.getNome())) {
			    Predicate nome = builder.like(from.<String>get("nome"), funcionario.getNome());
			    predicates.add(nome);
			}
			
			if(StringUtils.isNoneBlank(funcionario.getCpf())) {
			    Predicate cpf = builder.like(from.<String>get("cpf"), funcionario.getCpf());
			    predicates.add(cpf);
			}
			 
			query.where(predicates.toArray(new Predicate[]{}));
			
			return createQuery(query).getResultList();
		} catch (PlcException e) {
			throw e;
		} catch (Exception e) {
			throw PlcBeanMessages.FALHA_PERSISTENCIA_20.create(e.getMessage());
		}
		
	
	}

}