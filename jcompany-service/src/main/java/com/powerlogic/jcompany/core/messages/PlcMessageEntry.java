package com.powerlogic.jcompany.core.messages;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class PlcMessageEntry
{
   private PlcMessageKey key;

   private PlcMessageType type;

   private String message;

   private String[] args;

   public PlcMessageEntry(PlcMessageKey key, PlcMessageType type)
   {
      this.key = key;
      this.type = type;
   }

   public PlcMessageEntry(PlcMessageKey key, PlcMessageType type, String... args)
   {
      this(key, type);
      this.args = args;
   }

   public PlcMessageType getType()
   {
      return type;
   }

   public PlcMessageKey getKey()
   {
      return key;
   }

   public String getMessage()
   {
      if (message != null && !message.isEmpty())
      {
         return message;
      }
      return key.getName();
   }

   void setMessage(String message)
   {
      this.message = message;
   }

   public String[] getArgs()
   {
      return args;
   }

   void setArgs(String[] args)
   {
      this.args = args;
   }

   @Override
   public String toString()
   {
      return getMessage();
   }
}
