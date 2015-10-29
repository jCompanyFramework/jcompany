package com.powerlogic.jcompany.rhdemo.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.core.model.entity.PlcLogicalExclusion;
import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;

@Entity
@Table(name = "UNIDADEFEDERATIVA")
@SequenceGenerator(name = "UNIDADEFEDERATIVA_ID_GENERATOR", sequenceName = "SE_UNIDADEFEDERATIVA", allocationSize = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "UnidadeFederativaEntity.findByNome", query = "SELECT u FROM UnidadeFederativaEntity u WHERE u.nome = :nome AND u.situacao = :situacao") 
})
public class UnidadeFederativaEntity extends PlcVersionedEntity<Long> implements PlcLogicalExclusion {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNIDADEFEDERATIVA_ID_GENERATOR")
	@Column(name = "PK_UNIDADEFEDERATIVA", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "NOME", length = 100)
	@Size(max = 100) 
	@NotNull
	private String nome;

	@Column(name = "SIGLA", length = 2)
	@Size(max = 2) 
	@NotNull
	private String sigla;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
