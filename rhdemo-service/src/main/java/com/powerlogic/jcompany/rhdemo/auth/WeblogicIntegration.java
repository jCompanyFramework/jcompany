package com.powerlogic.jcompany.rhdemo.auth;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.security.auth.Subject;

import org.apache.commons.lang3.StringUtils;

import com.powerlogic.jcompany.core.rest.auth.PlcAuthenticatedUserInfo;

@ApplicationScoped
public class WeblogicIntegration
{

   public List<String> getRoles()
   {
      List<String> roles = new ArrayList<String>();
      try
      {
         Subject subject = getWLSubject();

         for (Principal principal : subject.getPrincipals())
         {
            // Weblogic Group is a Role!
            if (principal.getClass().getSimpleName().contains("WLSGroup"))
            {
               roles.add(principal.getName().toUpperCase());
            }
         }
      }
      catch (Exception e)
      {
      }
      return roles;
   }

   private Subject getWLSubject()
   {
      try
      {
         return (Subject) Class.forName("weblogic.security.Security").getMethod("getCurrentSubject").invoke(null);
      }
      catch (Exception e)
      {
         return null;
      }
   }

   public boolean loadExtraInfo(PlcAuthenticatedUserInfo userInfo, final Principal userPrincipal) 
   {
      boolean fieldRequired = true;
      
      Map<String, String> attributes = new HashMap<String, String>(); 
      attributes.put("NOME", "Administrator");
      attributes.put("EMAIL", "admin@powerlogic.com.br");
      
      if (attributes != null)
      {
         final String nome = attributes.get("NOME");
         final String email = attributes.get("EMAIL");

         if (StringUtils.isNotBlank(nome))
         {
            userInfo.setName(nome);
         }
         else
         {
            fieldRequired = false;
         }
         if (StringUtils.isNotBlank(email))
         {
            userInfo.setEmail(email);
         }
        
      }
     
      return fieldRequired;
   }

}
