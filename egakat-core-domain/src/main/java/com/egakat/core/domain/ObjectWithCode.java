package com.egakat.core.domain;

import java.io.Serializable;

public interface ObjectWithCode<ID extends Serializable> extends IdentifiedDomainObject<ID>{

	String getCodigo();

	void setCodigo(String codigo);
}
