package com.powerlogic.jcompany.rhdemo.auth;

import java.security.Principal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import com.powerlogic.jcompany.core.rest.auth.PlcAuthenticatedUserInfo;
import com.powerlogic.jcompany.core.rest.auth.PlcAuthenticationProvider;

@ApplicationScoped
@Specializes
public class AuthenticationProvider extends PlcAuthenticationProvider
{
	@Inject
	private WeblogicIntegration weblogicIntegration;

	@Override
	public PlcAuthenticatedUserInfo createUser(final Principal userPrincipal, final String host) 
	{
		PlcAuthenticatedUserInfo user = new PlcAuthenticatedUserInfo(userPrincipal.getName(), host, weblogicIntegration.getRoles());
		weblogicIntegration.loadExtraInfo(user, userPrincipal);
		return user;
	}


}
