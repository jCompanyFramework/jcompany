package com.powerlogic.jcompany.core.rest.cross;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class PlcCrossDomainFilter implements ContainerResponseFilter
{

   @Override
   public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext)
            throws IOException
   {

      String origin = requestContext.getHeaders().getFirst("Origin");
      if (origin != null)
      {
         responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);
      }

      responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
      responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
      responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
      responseContext.getHeaders().add("Access-Control-Max-Age", "0");
   }
}
