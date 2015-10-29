package com.powerlogic.jcompany.rhdemo.app.rest.entity;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.rest.PlcSearchBuilder;
import com.powerlogic.jcompany.core.rest.auth.PlcAuthenticated;
import com.powerlogic.jcompany.core.rest.entity.PlcAbstractEntityRest;
import com.powerlogic.jcompany.rhdemo.app.model.dto.UnidadeFederativaDTO;
import com.powerlogic.jcompany.rhdemo.app.model.entity.UnidadeFederativaEntity;
import com.powerlogic.jcompany.rhdemo.app.model.service.UnidadeFederativaService;

@PlcAuthenticated
@Path("/entity/unidadefederativa")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class UnidadeFederativaRest extends PlcAbstractEntityRest<Long, UnidadeFederativaEntity, Object> {
   @Inject
   private UnidadeFederativaService unidadeFederativaService;

   @GET
   @Path("/all")
   public List<UnidadeFederativaEntity> findAll() throws PlcException
   {
      return getEntityService().findAll();
   }

   @GET
   @Path("/findByNome")
   public UnidadeFederativaEntity find(@BeanParam PlcSearchBuilder searchBuilder, @QueryParam("nome") String nome) throws PlcException
   {
      return getEntityService().findByNome(nome);
   }

   @POST
   @Path("/salvarUnidadeFederativa")
   public UnidadeFederativaDTO salvarUsuario(UnidadeFederativaDTO uf) throws PlcException
   {
      return getEntityService().salvar(uf);
   }

   @POST
   @Path("/removerUnidadeFederativa")
   public boolean removerUsuario(UnidadeFederativaDTO uf) throws PlcException
   {
      return getEntityService().remover(uf);
   }

   @Override
   protected UnidadeFederativaService getEntityService()
   {
      return unidadeFederativaService;
   }
   
   @Override
	public UnidadeFederativaEntity save(@Valid UnidadeFederativaEntity entity) throws PlcException {
		// TODO Auto-generated method stub
		return super.save(entity);
	}

}
