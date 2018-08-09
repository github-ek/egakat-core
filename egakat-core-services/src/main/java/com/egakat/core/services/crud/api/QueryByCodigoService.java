package com.egakat.core.services.crud.api;

import java.util.Optional;

import com.egakat.core.domain.ObjectWithCode;

public interface QueryByCodigoService<M extends ObjectWithCode<ID>, ID> {

	Optional<M> findByCodigo(String codigo);

}
