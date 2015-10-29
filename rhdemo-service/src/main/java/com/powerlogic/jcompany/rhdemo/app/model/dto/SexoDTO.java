package com.powerlogic.jcompany.rhdemo.app.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.rhdemo.app.model.domain.Sexo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SexoDTO {
	
	public static final String ID_CACHE = "Sexo";

	public SexoDTO(Sexo sexo) {
		
		this.sexo = sexo;
		
		this.descricao = sexo.getLabel();
		
	}
	
	private Sexo sexo;
	
	private String descricao;

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
