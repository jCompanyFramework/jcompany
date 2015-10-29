package com.powerlogic.jcompany.rhdemo.app.model.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.rhdemo.app.model.domain.Sexo;
import com.powerlogic.jcompany.rhdemo.app.model.entity.DepartamentoEntity;
import com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario.CurriculoEntity;
import com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario.DependenteEntity;
import com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario.FotoEntity;
import com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario.HistoricoProfissionalEntity;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FuncionarioDTO {

	private Long id;
	
	private String nome;
	
	private String sigla;
	
	private Date dataNascimento;
	
	private String cpf;
	
	private String email;
	
	private Sexo sexo;
	
	private Boolean temCursoSuperior;
	
	private DepartamentoEntity departamento;
	
	private String observacao;
	
	private List<CurriculoEntity> curriculo;
	
    private FotoEntity foto;
	
	private List<HistoricoProfissionalEntity> historicoProfissional;
	
	private List<DependenteEntity> dependente;
	
	private int versao;
	
	private String usuarioAtualizacao;

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
