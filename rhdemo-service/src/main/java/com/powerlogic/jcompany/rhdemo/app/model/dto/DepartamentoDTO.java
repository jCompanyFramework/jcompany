package com.powerlogic.jcompany.rhdemo.app.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.rhdemo.app.model.entity.DepartamentoEntity;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DepartamentoDTO {

	private Long id;

	private String descricao;

	private DepartamentoEntity departamentoPai;

	private int versao;
	
	private String usuarioAtualizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public DepartamentoEntity getDepartamentoPai() {
		return departamentoPai;
	}

	public void setDepartamentoPai(DepartamentoEntity departamentoPai) {
		this.departamentoPai = departamentoPai;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public String getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}
}
