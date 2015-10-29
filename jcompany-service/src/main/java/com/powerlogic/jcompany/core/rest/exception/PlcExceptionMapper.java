package com.powerlogic.jcompany.core.rest.exception;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.messages.PlcMessageMap;
import com.powerlogic.jcompany.core.messages.PlcMessageTranslator;

@Provider
public class PlcExceptionMapper implements ExceptionMapper<PlcException> {
	
	private boolean shouldTranslate = false;

	@Inject
	private PlcMessageTranslator messageTranslate;

	@Override
	public Response toResponse(PlcException exception) {
		PlcMessageMap messageMap = exception.getMessageMap();

		if (shouldTranslate) {
			messageMap = translateMessageMap(messageMap);
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).entity(messageMap).build();
	}

	protected PlcMessageMap translateMessageMap(PlcMessageMap messageMap) {
		return messageTranslate.translate(messageMap);
	}
}
