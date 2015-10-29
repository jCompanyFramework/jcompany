package com.powerlogic.jcompany.rhdemo.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.core.model.entity.PlcLogicalExclusion;
import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;

@Entity
@Table(name = "DEPARTAMENTO")
@SequenceGenerator(name = "DEPARTAMENTO_ID_GENERATOR", sequenceName = "SE_DEPARTAMENTO", allocationSize = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "DepartamentoEntity.find", query = "SELECT u FROM DepartamentoEntity u WHERE u.descricao = :nome AND u.situacao = :situacao") 
})
public class DepartamentoEntity extends PlcVersionedEntity<Long> implements PlcLogicalExclusion {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTAMENTO_ID_GENERATOR")
	@Column(name = "PK_DEPARTAMENTO", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "DESCRICAO", length = 100)
	private String descricao;

	@ManyToOne (targetEntity = DepartamentoEntity.class, fetch = FetchType.LAZY)
	@JoinColumn (name = "ID_DEPARTAMENTO_PAI")
	private DepartamentoEntity departamentoPai;

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

}
