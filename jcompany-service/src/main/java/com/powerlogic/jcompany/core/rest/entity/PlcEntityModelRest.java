package com.powerlogic.jcompany.core.rest.entity;

import java.io.Serializable;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.commons.search.PlcPagedResult;
import com.powerlogic.jcompany.core.model.entity.PlcEntityModel;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PlcEntityModelRest<PK extends Serializable, E extends PlcEntityModel<PK>, A> {
	
   @GET
   @Path("/find")
   PlcPagedResult<E> find(@BeanParam A searchBuilder) throws PlcException;

   @GET
   @Path("/create")
   E newEntity() throws PlcException;

   @GET
   @Path("/get/{id}")
   E get(@PathParam("id") PK entityId) throws PlcException;

   @POST
   @Path("/save")
   E save(E entity) throws PlcException;

   @POST
   @Path("/remove")
   boolean remove(E entity) throws PlcException;
   
}
