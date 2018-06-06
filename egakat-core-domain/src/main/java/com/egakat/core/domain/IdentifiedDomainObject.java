package com.egakat.core.domain;

import java.io.Serializable;

public interface IdentifiedDomainObject<ID extends Serializable> extends Serializable {

	ID getId();

}
