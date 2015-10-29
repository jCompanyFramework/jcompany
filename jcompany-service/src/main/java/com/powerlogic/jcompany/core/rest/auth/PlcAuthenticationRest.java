package com.powerlogic.jcompany.core.rest.auth;

import java.security.Principal;
import java.util.Map;

import javax.inject.Inject;
import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.exception.PlcBeanMessages;
import com.powerlogic.jcompany.core.rest.PlcAbstractRest;

@Path("/auth")
@PlcAuthenticated
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class PlcAuthenticationRest extends PlcAbstractRest
{
   @Inject
   private PlcAuthenticationProvider authProvider;

   @GET
   @Path("/user")
   public PlcAuthenticatedUserInfo user(@Context HttpServletRequest request) throws PlcException
   {
      return getUserInfo(request);
   }



   @POST
   @Path("/login")
   @PlcNotAuthenticated
   public PlcAuthenticatedUserInfo login(@Context HttpServletRequest request, Map<String, String> data)
            throws PlcException
   {
      String username = data.get("username");
      String password = data.get("password");

      if (username == null || username.isEmpty() || password == null || password.isEmpty())
      {
         throw new WebApplicationException(Status.BAD_REQUEST);
      }
      // authenticate!
      try
      {
    	  if (request.getUserPrincipal()==null) {
    		  request.login(username, password);
    	  }
      }
      catch (Exception e)
      {
         // e.printStackTrace();
         userLogout(request);
         if (e.getCause() instanceof FailedLoginException)
         {
            throw PlcBeanMessages.FALHA_LOGIN_001.create();
         }
         throw PlcBeanMessages.FALHA_OPERACAO_003.create();
      }
      // create user info!
      return userLogin(request);
   }

   @POST
   @Path("/logout")
   @PlcNotAuthenticated
   public boolean logout(@Context HttpServletRequest request)
   {
      PlcAuthenticatedUserInfo userInfo = userLogout(request);

      if (userInfo != null)
      {
         return true;
      }
      return false;
   }

   protected PlcAuthenticatedUserInfo getUserInfo(HttpServletRequest request) throws PlcException
   {
      HttpSession session = request.getSession(false);

      if (session != null)
      {
         PlcAuthenticatedUserInfo user = (PlcAuthenticatedUserInfo) session.getAttribute(PlcAuthenticatedUserInfo.PROPERTY);
         return user;
      }
      return null;
   }

   @GET
   @Path("/checkSession")
   @PlcNotAuthenticated
   public PlcAuthenticatedUserInfo checkSession(@Context HttpServletRequest request) throws PlcException
   {
      if (request != null)
      {
         HttpSession session = request.getSession(false);

         if (session != null)
         {
            return (PlcAuthenticatedUserInfo) session.getAttribute(PlcAuthenticatedUserInfo.PROPERTY);
         }
      }
      return null;
   }

   protected PlcAuthenticatedUserInfo userLogin(HttpServletRequest request) throws PlcException
   {
      try
      {
         Principal userPrincipal = request.getUserPrincipal();
         PlcAuthenticatedUserInfo userInfo;
         userInfo = authProvider.createUser(userPrincipal, request.getRemoteHost());
         request.getSession().setAttribute(PlcAuthenticatedUserInfo.PROPERTY, userInfo);
         return userInfo;
      }
      catch (PlcException e)
      {
         userLogout(request);
         throw e;
      }
      catch (Exception e)
      {
         userLogout(request);
         throw PlcBeanMessages.FALHA_OPERACAO_003.create();
      }
   }

   protected PlcAuthenticatedUserInfo userLogout(HttpServletRequest request)
   {
      HttpSession session = request.getSession(false);

      if (session != null)
      {
         PlcAuthenticatedUserInfo userInfo = (PlcAuthenticatedUserInfo) session.getAttribute(PlcAuthenticatedUserInfo.PROPERTY);
         if (userInfo != null)
         {
            session.removeAttribute(PlcAuthenticatedUserInfo.PROPERTY);
         }
         session.invalidate();
         return userInfo;
      }
      return null;
   }
}
