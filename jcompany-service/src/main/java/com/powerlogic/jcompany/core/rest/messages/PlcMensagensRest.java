package com.powerlogic.jcompany.core.rest.messages;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.powerlogic.jcompany.core.messages.PlcMessageMap;
import com.powerlogic.jcompany.core.messages.PlcMessageTranslator;
import com.powerlogic.jcompany.core.rest.auth.PlcNotAuthenticated;

@PlcNotAuthenticated
@Path("/mensagens")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class PlcMensagensRest
{
   @Inject
   private PlcMessageTranslator messageTranslator;

   @GET
   public PlcMessageMap getMensagens()
   {
      return messageTranslator.getAllMessages();
   }
}
