package com.powerlogic.jcompany.core.model.entity;

import java.io.Serializable;

public interface PlcEntityModel<PK extends Serializable> extends Serializable {

	PK getId();

	void setId(PK id);
	
}