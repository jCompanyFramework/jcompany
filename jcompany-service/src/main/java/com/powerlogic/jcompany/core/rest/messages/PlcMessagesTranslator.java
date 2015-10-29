package com.powerlogic.jcompany.core.rest.messages;

import java.util.ResourceBundle;

import com.powerlogic.jcompany.core.messages.PlcMessageTranslator;

public class PlcMessagesTranslator extends PlcMessageTranslator
{
   private static final String MESSAGE_PLC = "PlcMessages";
   
   private static final String MESSAGE_APP = "AppMessages";

   public PlcMessagesTranslator()
   {
      super(MESSAGE_PLC);
      try {
    	  ResourceBundle b = ResourceBundle.getBundle(MESSAGE_APP);
    	  if (b!=null) {
    		  getAllMessages().addAll(getAllMessages(b));
    	  }
      } catch (Exception e) {}
   }
}
