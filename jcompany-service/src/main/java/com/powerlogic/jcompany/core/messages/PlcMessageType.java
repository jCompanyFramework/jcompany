package com.powerlogic.jcompany.core.messages;

public enum PlcMessageType
{
   SUCCESS, INFO, WARNING, ERROR;

   private String lowerName;

   private PlcMessageType()
   {
      lowerName = name().toLowerCase();
   }

   public String lowerName()
   {
      return lowerName;
   }

   public static PlcMessageType valueOrNull(String name)
   {
      for (PlcMessageType type : values())
      {
         if (type.name().equalsIgnoreCase(name))
         {
            return type;
         }
      }
      return null;
   }
}
