package com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.core.model.entity.PlcLogicalExclusion;
import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;

/**
 * Arquivo de Fotos
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CURRICULO")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceGenerator(name="CURRICULO_ID_GENERATOR",sequenceName="SE_CURRICULO")
public class CurriculoEntity extends PlcVersionedEntity<Long> implements PlcLogicalExclusion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRICULO_ID_GENERATOR")
	@Column(name = "PK_CURRICULO", unique = true, nullable = false, precision = 10)
	private Long id;
	
	@Column (name = "NOME")
	private String nome;

	@Column (name = "TIPO")
    protected String tipo;
	
	@Column (name = "TAMANHO")
    protected Integer tamanho;
	
    @OneToOne(targetEntity=CurriculoConteudoEntity.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    protected CurriculoConteudoEntity conteudo;

    protected String url;	

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public CurriculoConteudoEntity getBinaryContent() {
    	return( conteudo );
    }
	
	public String getNome() {
		return nome;
	}
	
    public String getType() {
    	return tipo;
    }

    public Integer getLength() {
    	return tamanho;
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}