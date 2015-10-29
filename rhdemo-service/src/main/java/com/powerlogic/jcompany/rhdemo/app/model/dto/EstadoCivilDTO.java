package com.powerlogic.jcompany.rhdemo.app.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.rhdemo.app.model.domain.EstadoCivil;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EstadoCivilDTO {

	public static final String ID_CACHE = "EstadoCivil";
	
	public EstadoCivilDTO(EstadoCivil estadoCivil) {
		
		this.estadoCivil = estadoCivil;
		
		this.descricao = estadoCivil.getLabel();
		
	}
	
	private EstadoCivil estadoCivil;
	
	private String descricao;

	public EstadoCivil getSexo() {
		return estadoCivil;
	}

	public void setSexo(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
