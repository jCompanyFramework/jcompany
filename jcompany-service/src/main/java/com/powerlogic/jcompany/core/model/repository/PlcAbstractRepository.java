package com.powerlogic.jcompany.core.model.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;

public abstract class PlcAbstractRepository<PK extends Serializable, E extends PlcVersionedEntity<PK>> extends PlcBaseAbstractRepository<PK, E>
{
   @PersistenceContext(unitName = "jcompany-persistence-unit")
   private EntityManager entityManager;

   @Override
   protected EntityManager getEntityManager()
   {
      return entityManager;
   }
}
