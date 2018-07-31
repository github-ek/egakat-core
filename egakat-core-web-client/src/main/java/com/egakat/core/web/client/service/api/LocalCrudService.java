package com.egakat.core.web.client.service.api;

public interface LocalCrudService<M, ID> extends LocalQueryService<M, ID> {

	M create(M model);

	M update(M model);

	void delete(ID id);

	void delete(ID id, int version);
}