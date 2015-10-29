package com.powerlogic.jcompany.core.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.powerlogic.jcompany.core.listener.PlcVersionedListener;
import com.powerlogic.jcompany.core.model.domain.PlcSituacao;

@MappedSuperclass
@EntityListeners(PlcVersionedListener.class)
public abstract class PlcVersionedEntity<PK extends Serializable> implements PlcEntityModel<PK> {
	
	private static final long serialVersionUID = 1L;

	@Version
	@NotNull
	@Column(name = "VERSAO_REGISTRO")
	@Digits(integer = 8, fraction = 0)
	private int versao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CRIACAO", updatable = false)
	private Date dataCriacao;

	@Column(name = "NM_ULT_ALTERACAO", length = 150)
	private String usuarioAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ULT_ALTERACAO")
	private Date dataAtualizacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "IN_SITUACAO_REGISTRO", nullable = false, length = 1)
	private PlcSituacao situacao;

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public PlcSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(PlcSituacao situacao) {
		this.situacao = situacao;
	}
}
