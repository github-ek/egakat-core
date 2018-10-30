package com.egakat.core.data.jpa.repository;

import java.util.Optional;

import com.egakat.core.domain.ObjectWithCode;

public interface QueryByCodigo<E extends ObjectWithCode<ID>, ID>{
	Optional<E> findByCodigo(String codigo);
}