package com.powerlogic.jcompany.core.rest.auth;

import java.security.Principal;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;

import com.powerlogic.jcompany.core.PlcException;

@ApplicationScoped
public class PlcAuthenticationProvider {
	

	public PlcAuthenticatedUserInfo createUser(final Principal userPrincipal, final String host) throws PlcException {
		PlcAuthenticatedUserInfo user = new PlcAuthenticatedUserInfo(userPrincipal.getName(), host, new ArrayList<String>());
		return user;
	}
}
