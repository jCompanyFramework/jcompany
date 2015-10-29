package com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.core.model.entity.PlcLogicalExclusion;
import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;
import com.powerlogic.jcompany.rhdemo.app.model.domain.EstadoCivil;
import com.powerlogic.jcompany.rhdemo.app.model.domain.Sexo;
import com.powerlogic.jcompany.rhdemo.app.model.entity.DepartamentoEntity;

/**
 * Classe Concreta gerada a partir do assistente
 */
@Entity
@Table(name = "FUNCIONARIO")
@SequenceGenerator(name = "SE_FUNCIONARIO", sequenceName = "SE_FUNCIONARIO")
@Access(AccessType.FIELD)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "FuncionarioEntity.find", query = "SELECT f from FuncionarioEntity f where f.situacao = :situacao order by f.id asc")
})
public class FuncionarioEntity extends PlcVersionedEntity<Long> implements PlcLogicalExclusion {

	public static final int MAIORIDADE = 18;

	public static final BigDecimal SALARIO_MINIMO_SUPERIOR = new BigDecimal(1000);
	
	@Id 
 	@GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_FUNCIONARIO")
	@Column(nullable=false,length=5)
	private Long id;
	
	@NotNull 
	@Size(max = 40) 
	@Column
	private String nome;
	
	@Past
	@NotNull 
	@Column(length=11) 
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@NotNull 
	@Size(max = 11) 
	@Column
	private String cpf;
	
	@Size(max = 60) 
	@Column
	private String email;
	
	@Enumerated(EnumType.STRING)
	@NotNull 
	@Column(length=1)
	private EstadoCivil estadoCivil;
	
	@Enumerated(EnumType.STRING)
	@NotNull 
	@Column(length=1)
	private Sexo sexo;
	
	@NotNull 
	@Column(length=1)
	private Boolean temCursoSuperior = false;
	
	@ManyToOne (targetEntity = DepartamentoEntity.class, fetch = FetchType.LAZY)
	@JoinColumn
	private DepartamentoEntity departamento;
	
	@Size(max = 255) @Column
	private String observacao;
	
    @OneToMany (targetEntity = CurriculoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @OrderBy("id")
    @Valid
    private List<CurriculoEntity> curriculo;

    @OneToOne (targetEntity = FotoEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)    
    @JoinColumn(name="ID_FOTO")
    private FotoEntity foto;
	
	@NotNull
	@Size(max = 1) @Column
	private String sitHistoricoPlc="A";
	
	@OneToMany (targetEntity = HistoricoProfissionalEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="funcionario")
	@Valid
	private List<HistoricoProfissionalEntity> historicoProfissional;
	
	@OneToMany (targetEntity = DependenteEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="funcionario")
	@Valid
	private List<DependenteEntity> dependente;
	
	@AssertTrue(message="{funcionario.valida.maioridade}")
	public boolean isMaiorDeIdade() {
		//TODO: FAZER O CALCULO...
		return true;
	}	

	public FuncionarioEntity() {
	}

	@Override
	public String toString() {
		return getNome();
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Boolean getTemCursoSuperior() {
		return temCursoSuperior;
	}

	public void setTemCursoSuperior(Boolean temCursoSuperior) {
		this.temCursoSuperior = temCursoSuperior;
	}

	public DepartamentoEntity getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoEntity departamento) {
		this.departamento = departamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<CurriculoEntity> getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(List<CurriculoEntity> curriculo) {
		this.curriculo = curriculo;
	}

	public FotoEntity getFoto() {
		return foto;
	}

	public void setFoto(FotoEntity foto) {
		this.foto = foto;
	}

	public String getSitHistoricoPlc() {
		return sitHistoricoPlc;
	}

	public void setSitHistoricoPlc(String sitHistoricoPlc) {
		this.sitHistoricoPlc = sitHistoricoPlc;
	}

	public List<HistoricoProfissionalEntity> getHistoricoProfissional() {
		return historicoProfissional;
	}

	public void setHistoricoProfissional(List<HistoricoProfissionalEntity> historicoProfissional) {
		this.historicoProfissional = historicoProfissional;
	}

	public List<DependenteEntity> getDependente() {
		return dependente;
	}

	public void setDependente(List<DependenteEntity> dependente) {
		this.dependente = dependente;
	}

}
