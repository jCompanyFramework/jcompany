package com.powerlogic.jcompany.core;

import javax.ejb.ApplicationException;

import com.powerlogic.jcompany.core.messages.PlcMessageKey;
import com.powerlogic.jcompany.core.messages.PlcMessageMap;
import com.powerlogic.jcompany.core.messages.PlcMessageType;

@ApplicationException(rollback = true)
public class PlcException extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   private PlcMessageMap messageMap;

   public PlcException(PlcMessageKey key)
   {
      addMessage(key);
   }

   public PlcException(PlcMessageKey key, String... args)
   {
      addMessage(key, args);
   }

   public PlcException(Throwable cause, PlcMessageKey key)
   {
      super(cause);
      addMessage(key);
   }

   public PlcException(Throwable cause, PlcMessageKey key, String... args)
   {
      super(cause);
      addMessage(key, args);
      addThrowable(cause);
   }

   public PlcException(PlcMessageMap messageMap)
   {
      this.messageMap = messageMap;
   }

   public PlcException(Throwable cause, PlcMessageMap messageMap)
   {
      super(cause);
      this.messageMap = messageMap;
   }

   protected void addMessage(PlcMessageKey key, String... args)
   {
      getMessageMap().addMessage(key, PlcMessageType.ERROR, args);
   }

   protected void addThrowable(Throwable throwable)
   {
      if (throwable != this && throwable instanceof PlcException)
      {
         getMessageMap().addAll(((PlcException) throwable).getMessageMap());
      }
   }

   public PlcMessageMap getMessageMap()
   {
      if (messageMap == null)
      {
         messageMap = new PlcMessageMap();
      }
      return messageMap;
   }
}
