package com.powerlogic.jcompany.rhdemo.app.rest.entity;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.StringUtils;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.rest.PlcSearchBuilder;
import com.powerlogic.jcompany.core.rest.auth.PlcAuthenticated;
import com.powerlogic.jcompany.core.rest.entity.PlcAbstractEntityRest;
import com.powerlogic.jcompany.rhdemo.app.model.entity.DepartamentoEntity;
import com.powerlogic.jcompany.rhdemo.app.model.service.DepartamentoService;

@PlcAuthenticated
@Path("/entity/departamento")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class DepartamentoRest extends PlcAbstractEntityRest<Long, DepartamentoEntity, Object> {
	
   @Inject
   private DepartamentoService departamentoService;

   @GET
   @Path("/findByRoot")
   public DepartamentoEntity findByRoot(@BeanParam PlcSearchBuilder searchBuilder, @QueryParam("root") Long id) throws PlcException {
      return getEntityService().findByRoot(id);
   }

   @GET
   @Path("/all")
   public List<DepartamentoEntity> findAll(@Context HttpServletRequest request, @Context UriInfo ui) throws PlcException {
    
	   MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

	   DepartamentoEntity departamento = new DepartamentoEntity();
	   
	   String descricao = queryParams.getFirst("descricao");
	   
	   String departamentoPai = queryParams.getFirst("departamentoPai");
	   
	   if(StringUtils.isNotBlank(descricao)) {
		   departamento.setDescricao(descricao);
	   }
	   
	   if(StringUtils.isNotBlank(departamentoPai)) {

		   DepartamentoEntity departamentoParam = new DepartamentoEntity();
		   departamentoParam.setId(new Long(departamentoPai));
		   departamento.setDepartamentoPai(departamentoParam);
		   
	   }
	   
	   return getEntityService().findAll(departamento);
   }
   
   @Override
   protected DepartamentoService getEntityService() {
      return departamentoService;
   }

}