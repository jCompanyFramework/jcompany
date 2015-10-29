/*  																													
	    			       Jaguar-jCompany Developer Suite.																		
			    		        Powerlogic 2010-2014.
			    		    
		Please read licensing information in your installation directory.Contact Powerlogic for more 
		information or contribute with this project: suporte@powerlogic.com.br - www.powerlogic.com.br																								
*/
package com.powerlogic.jcompany.rhdemo.app.model.entity.funcionario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.powerlogic.jcompany.core.model.entity.PlcLogicalExclusion;
import com.powerlogic.jcompany.core.model.entity.PlcVersionedEntity;

/**
 * Arquivo de conteúdos de curríulos
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CURRICULO_CONTEUDO")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceGenerator(name="CURRICULO_CONTEUDO_ID_GENERATOR",sequenceName="SE_CURRICULO_CONTEUDO")
public class CurriculoConteudoEntity extends PlcVersionedEntity<Long> implements PlcLogicalExclusion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRICULO_CONTEUDO_ID_GENERATOR")
	@Column(name = "PK_CURRICULO_CONTEUDO", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "CONTEUDO_BINARIO", length = Integer.MAX_VALUE)
	protected byte[] binaryContent;

	public byte[] getBinaryContent() {
		return binaryContent;
	}
	
    public void setBinaryContent( byte[] content)    {
    	this.binaryContent = content;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}