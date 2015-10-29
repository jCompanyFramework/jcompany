package com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.core.model.entity.PlcLogicalExclusion;
import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;
import com.powerlogic.jcompany.rhdemo.app.model.domain.Sexo;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="DEPENDENTE")
@SequenceGenerator(name="SE_DEPENDENTE", sequenceName="SE_DEPENDENTE")
@Access(AccessType.FIELD)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DependenteEntity extends PlcVersionedEntity<Long> implements PlcLogicalExclusion {

	private static final long serialVersionUID = 1L;
	
	@Id 
 	@GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_DEPENDENTE")
	@Column(nullable=false,length=5)
	private Long id;

	@Size(max = 40)
	@Column
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(length=1)
	private Sexo sexo;
	
	@ManyToOne (targetEntity = FuncionarioEntity.class, fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn
	private FuncionarioEntity funcionario;	
 	
    /*
     * Construtor padrão
     */
    public DependenteEntity() {
    }
	@Override
	public String toString() {
		return getNome();
	}

	@Transient
	private transient String indExcPlc = "N";	

	public void setIndExcPlc(String indExcPlc) {
		this.indExcPlc = indExcPlc;
	}

	public String getIndExcPlc() {
		return indExcPlc;
	}
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
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

}