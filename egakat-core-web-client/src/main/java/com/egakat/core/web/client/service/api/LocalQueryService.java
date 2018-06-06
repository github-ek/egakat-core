package com.egakat.core.web.client.service.api;

import java.io.Serializable;
import java.util.List;

import com.egakat.core.domain.IdentifiedDomainObject;

public interface LocalQueryService<M extends IdentifiedDomainObject<ID> , ID extends Serializable> {

	M findOneById(ID id);
	
	List<M> findAllById(List<ID> ids);
}