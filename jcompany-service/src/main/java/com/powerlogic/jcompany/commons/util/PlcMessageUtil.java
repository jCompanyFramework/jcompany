/*  																													
	    			       Jaguar-jCompany Developer Suite.																		
			    		        Powerlogic 2010-2014.

		Please read licensing information in your installation directory.Contact Powerlogic for more 
		information or contribute with this project: suporte@powerlogic.com.br - www.powerlogic.com.br																								
 */

package com.powerlogic.jcompany.commons.util;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.StringUtils;

public class PlcMessageUtil {

	public String[]  availableInvariantMessages(Set<ConstraintViolation<Object>> validationMessages)  {

		if (validationMessages!=null) {
			String[] mensagens = new String[validationMessages.size()];

			String msg=null;
			String msgCampo=null;

			Iterator<ConstraintViolation<Object>> iterator = validationMessages.iterator();
			int i=0;
			while(iterator.hasNext()) {
				ConstraintViolation<Object> cv = iterator.next();

				msg = cv.getMessage();

				int posIni = msg.indexOf("{");
				if (posIni>-1) {
					int posFim = msg.indexOf("}");
					String token = msg.substring(posIni,posFim+1);
					msgCampo = cv.getPropertyPath().toString();
					msg = StringUtils.replaceOnce(msg,token,msgCampo);
				}
				mensagens[i++] = msg;
				
			}
			return mensagens;
		}
		return null;

	}

}
