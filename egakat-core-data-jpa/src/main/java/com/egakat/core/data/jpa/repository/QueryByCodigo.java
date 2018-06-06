package com.egakat.core.data.jpa.repository;

import java.io.Serializable;
import java.util.Optional;

import com.egakat.core.domain.ObjectWithCode;

public interface QueryByCodigo<T extends ObjectWithCode<ID>, ID extends Serializable>{
	Optional<T> findByCodigo(String codigo);
}