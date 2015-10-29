package com.powerlogic.jcompany.core.rest;

import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

public abstract class PlcAbstractRest
{
   @GET
   public Response index()
   {
      return responseText("REST-API: " + getClass()).build();
   }

   protected ResponseBuilder responseText(Object result)
   {
      return Response.ok(result, MediaType.TEXT_PLAIN);
   }

   protected ResponseBuilder responseJson(Object result)
   {
      return Response.ok(result, MediaType.APPLICATION_JSON);
   }
}
