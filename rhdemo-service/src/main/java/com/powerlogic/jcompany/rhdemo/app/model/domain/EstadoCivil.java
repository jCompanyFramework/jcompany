/*  																													
	    			       Jaguar-jCompany Developer Suite.																		
			    		        Powerlogic 2010-2014.
			    		    
		Please read licensing information in your installation directory.Contact Powerlogic for more 
		information or contribute with this project: suporte@powerlogic.com.br - www.powerlogic.com.br																								
*/
package com.powerlogic.jcompany.rhdemo.app.model.domain;

/**
 * Valores discretos para Estado Civil, com descricoes I18n
 */
public enum EstadoCivil {
    
	C("Casado"),
	S("Solteiro"),
	D("Divorciado"),
	V("Viúvo");

	
    /**
     * @return Retorna o codigo.
     */
	private String label;
    
    private EstadoCivil(String label) {
    	this.label = label;
    }
     
    public String getLabel() {
        return label;
    }
}
