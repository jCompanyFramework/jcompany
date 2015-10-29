package com.powerlogic.jcompany.rhdemo.app.rest.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.rest.auth.PlcAuthenticated;
import com.powerlogic.jcompany.rhdemo.app.model.domain.EstadoCivil;
import com.powerlogic.jcompany.rhdemo.app.model.domain.Sexo;
import com.powerlogic.jcompany.rhdemo.app.model.dto.EstadoCivilDTO;
import com.powerlogic.jcompany.rhdemo.app.model.dto.SexoDTO;
import com.powerlogic.jcompany.rhdemo.app.model.entity.UnidadeFederativaEntity;
import com.powerlogic.jcompany.rhdemo.app.model.service.UnidadeFederativaService;

@PlcAuthenticated
@Path("/entity/lookup")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class LookupRest {

   @Inject
   private UnidadeFederativaService unidadeFederativaService;	

   @GET
   @Path("/sexo")
   public Response findSexoLookup(@Context HttpHeaders hh, @Context Request request) throws PlcException {
	   
	   List<SexoDTO> sexos = new ArrayList<SexoDTO>();
	   
	   for (Sexo sexo : Arrays.asList(Sexo.values())) {
		   sexos.add(new SexoDTO(sexo));
	   }
	   
	   return cache(request, sexos, SexoDTO.ID_CACHE);
   }


   @GET
   @Path("/estadocivil")
   public Response findEstadoCivilLookup(@Context HttpHeaders hh, @Context Request request) throws PlcException {
	   
	   List<EstadoCivilDTO> estadosCivis = new ArrayList<EstadoCivilDTO>();
	   
	   for (EstadoCivil estadosCivil : Arrays.asList(EstadoCivil.values())) {
		   estadosCivis.add(new EstadoCivilDTO(estadosCivil));
	   }
	   
	   return cache(request, estadosCivis, EstadoCivilDTO.ID_CACHE);	   
   }

   @GET
   @Path("/unidadefederativa")
   public Response getOrder(@Context HttpHeaders hh, @Context Request request) throws PlcException {
	   
	   List<UnidadeFederativaEntity> unidades = unidadeFederativaService.findAll();
    
	   Date data = Calendar.getInstance().getTime();
	   
	   EntityTag etag = new EntityTag("unidadefederativa");
	   
	   Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(etag);

	   if (responseBuilder != null) {
		   return responseBuilder.build();
	   }

	   responseBuilder = Response.ok(unidades).tag(etag);

	   return responseBuilder.build();
   }
    
	private <E> Response cache(Request request, List< E > lista, String name) {
		
		Date data = Calendar.getInstance().getTime();
   
		final EntityTag eTag = new EntityTag( name );
	    
		final CacheControl cacheControl = new CacheControl();
	    
		cacheControl.setMaxAge(5000);
	
		ResponseBuilder builder = request.evaluatePreconditions(data, eTag);
	
		// the resoruce's information was modified, return it
		if (builder == null) {
	        builder = Response.ok(lista);
		}
	
	   // the resource's information was not modified, return a 304
	   return builder.cacheControl(cacheControl).lastModified(data).tag(eTag).build();
	}
}
