package com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.core.model.entity.PlcLogicalExclusion;
import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;
/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name="HISTORICO_PROFISSIONAL")
@SequenceGenerator(name="SE_HISTORICO_PROFISSIONAL", sequenceName="SE_HISTORICO_PROFISSIONAL")
@Access(AccessType.FIELD)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoricoProfissionalEntity extends PlcVersionedEntity<Long> implements PlcLogicalExclusion {

	private static final long serialVersionUID = 1L;

	private static int SAL_MIN_CURSO_SUPERIOR=1000;
	
	@Id 
 	@GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_HISTORICO_PROFISSIONAL")
	@Column(nullable=false,length=5)
	private Long id;
	
	@ManyToOne (targetEntity = FuncionarioEntity.class, fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn
	private FuncionarioEntity funcionario;
	
	@Size(max = 40)
	@Column
	private String descricao;
	
	@Past
	@NotNull
	@Column(length=11)
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Min(value=0)
	@NotNull
	@Column(length=11, precision=11, scale=2)
	private BigDecimal salario;

	/**
	 * @return true Se funcionário não tiver curso superior ou tiver e salario for maior que 1.000,000
	 */
	@AssertTrue(message="{funcionario.valida.salario}")
	@Transient
	public boolean isSalarioValido() {
		
		if (!this.funcionario.getTemCursoSuperior() || this.salario == null) {
			return true;
		}
		
		return this.funcionario.getTemCursoSuperior() && this.salario.compareTo(new BigDecimal(SAL_MIN_CURSO_SUPERIOR)) >= 0;
		
	}	
 	
    /*
     * Construtor padrão
     */
    public HistoricoProfissionalEntity() {
    
    }
    
	@Override
	public String toString() {
		return getDescricao();
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

	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

}
