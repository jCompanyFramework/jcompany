package com.powerlogic.jcompany.core.rest.exception;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import com.powerlogic.jcompany.core.messages.PlcMessageEntry;
import com.powerlogic.jcompany.core.messages.PlcMessageMap;

@Provider
public class PlcExceptionInterceptor implements WriterInterceptor
{
   @Override
   public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException
   {
      Object entity = context.getEntity();
      if (entity instanceof PlcMessageMap || entity instanceof PlcMessageEntry)
      {
         context.setGenericType(null);
      }
      context.proceed();
   }
}
