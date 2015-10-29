package com.powerlogic.jcompany.core.rest.auth;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class PlcAuthenticationFeature implements DynamicFeature
{
   private static final Logger LOGGER = LoggerFactory.getLogger(PlcAuthenticationFeature.class);

   @Override
   public void configure(ResourceInfo resourceInfo, FeatureContext context)
   {
      PlcAuthenticated authenticated = getAuthenticated(resourceInfo);

      if (authenticated != null)
      {
         LOGGER.debug("Authenticated REST Resource {}", resourceInfo.getResourceMethod());

         context.register(createFilter(authenticated));
      }
   }

   protected PlcAuthenticationFilter createFilter(PlcAuthenticated authenticated)
   {
      return new PlcAuthenticationFilter(authenticated);
   }

   private PlcAuthenticated getAuthenticated(ResourceInfo resourceInfo)
   {
      // method override class authentication
      if (resourceInfo.getResourceMethod().isAnnotationPresent(PlcNotAuthenticated.class))
      {
         return null;
      }
      // method override class authentication
      PlcAuthenticated authenticated = resourceInfo.getResourceMethod().getAnnotation(PlcAuthenticated.class);
      if (authenticated == null)
      {
         // class indicate default authentication
         authenticated = resourceInfo.getResourceClass().getAnnotation(PlcAuthenticated.class);
      }
      return authenticated;
   }
}
