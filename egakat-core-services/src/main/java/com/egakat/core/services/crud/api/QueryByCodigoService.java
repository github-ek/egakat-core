package com.egakat.core.services.crud.api;

import java.io.Serializable;
import java.util.Optional;

import com.egakat.core.domain.ObjectWithCode;

public interface QueryByCodigoService<M extends ObjectWithCode<ID>, ID extends Serializable> {

	Optional<M> findByCodigo(String codigo);

}
