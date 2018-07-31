package com.egakat.core.web.client.service.api;

import java.util.List;

public interface LocalQueryService<M, ID> {

	M findOneById(ID id);

	List<M> findAllById(List<ID> ids);
}