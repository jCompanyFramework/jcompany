package com.powerlogic.jcompany.core.exception;

import com.powerlogic.jcompany.core.PlcException;
import com.powerlogic.jcompany.core.messages.PlcMessageKey;

public enum PlcBeanMessages implements PlcMessageKey
{
	DADOS_SALVOS_SUCESSO_000,
	FALHA_LOGIN_001,
	FALHA_SOLICITACAO_002,
	FALHA_OPERACAO_003,
	REGISTROS_NAO_ENCONTADOS_004,
	PESQUISA_MINIMA_005,
	PESQUISA_VAZIA_006,
	CAMPOS_OBRIGATORIOS_TOPICO_007,
	ICONES_OBRIGATORIOS_008,
	ICONES_TAMANHO_MAXIMO_009,
	REGISTROS_DUPLICADOS_010,
	REGISTROS_CONCORRENTES_011,
	FALHA_SOLICITACAO_012,
	CAMPO_TAMANHO_MINIMO_013,
	CAMPO_OBRIGATORIO_014,
	ELEMENTOS_OBRIGATORIOS_015,
	CAMPO_TAMANHO_MINIMO_016,
	CAMPO_TAMANHO_MAXIMO_017,
	CAMPO_TAMANHO_EXATO_018,
	UNSUPPORTED_OPERATION_019,
	FALHA_PERSISTENCIA_20,
	REGISTRO_EXCLUIDO_SUCESSO_021,
	NENHUM_REGISTRO_ENCONTRADO_022,
	FALHA_VALIDACAO_023,
	CAMPOS_OBRIGATORIOS_TOPICO_024
	;

   public String getName()
   {
      return name();
   }

   public PlcException create()
   {
      return new PlcException(this);
   }

   public PlcException create(String... args)
   {
      return new PlcException(this, args);
   }
}
