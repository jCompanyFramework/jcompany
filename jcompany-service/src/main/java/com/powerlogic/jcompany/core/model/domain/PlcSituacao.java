package com.powerlogic.jcompany.core.model.domain;

/**
 * Enum que representa a situação do registro na base de dados.
 */
public enum PlcSituacao {

   A /* situacao.A=Ativo */,
   I /* situacao.I=Inativo */,
   E /* situacao.E=Excluido */;

   /**
    * @return Retorna o codigo.
    */
   public String getCodigo()
   {
      return this.toString();
   }

}
