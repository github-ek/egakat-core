package com.egakat.core.data.jpa.domain;

import java.util.Objects;

import javax.persistence.MappedSuperclass;

import com.egakat.core.domain.IdentifiedDomainObject;

import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
@ToString
@NoArgsConstructor
public abstract class BaseEntity<ID> implements IdentifiedDomainObject<ID> {

	/**
	 * This `hashCode` implementation is specific for JPA entities and uses a fixed
	 * `int` value to be able to identify the entity in collections after a new id
	 * is assigned to the entity, following the article in
	 * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (getId() == null)
			return false;

		@SuppressWarnings("unchecked")
		BaseEntity<ID> other = (BaseEntity<ID>) obj;

		return Objects.equals(getId(), other.getId());
	}
}
