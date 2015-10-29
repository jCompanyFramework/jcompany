package com.powerlogic.jcompany.core.model.domain;

public enum PlcBoolean {

   Y /* plcBoolean.Y=Sim */,
   N /* plcBoolean.N=Não */;

   /**
    * @return Retorna o codigo.
    */
   public String getCodigo()
   {
      return this.toString();
   }

}
