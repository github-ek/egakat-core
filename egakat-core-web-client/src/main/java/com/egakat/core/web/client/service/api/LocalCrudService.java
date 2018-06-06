package com.egakat.core.web.client.service.api;

import java.io.Serializable;

import com.egakat.core.domain.IdentifiedDomainObject;

public interface LocalCrudService<M extends IdentifiedDomainObject<ID>, ID extends Serializable>
		extends LocalQueryService<M, ID> {

	M create(M model);

	M update(M model);

	void delete(ID id);

	void delete(ID id, int version);
}