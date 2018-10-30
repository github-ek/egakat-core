package com.egakat.core.data.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.egakat.core.domain.IdentifiedDomainObject;

@NoRepositoryBean
public interface IdentifiedDomainObjectRepository<E extends IdentifiedDomainObject<ID>, ID>
		extends JpaRepository<E, ID> {
}