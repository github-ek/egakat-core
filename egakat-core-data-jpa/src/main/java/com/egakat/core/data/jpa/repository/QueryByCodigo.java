package com.egakat.core.data.jpa.repository;

import java.util.Optional;

import com.egakat.core.domain.ObjectWithCode;

public interface QueryByCodigo<T extends ObjectWithCode<ID>, ID>{
	Optional<T> findByCodigo(String codigo);
}