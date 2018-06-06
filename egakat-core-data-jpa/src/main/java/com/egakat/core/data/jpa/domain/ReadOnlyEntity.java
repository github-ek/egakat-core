package com.egakat.core.data.jpa.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.egakat.core.domain.IdentifiedDomainObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter(value = AccessLevel.PRIVATE)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class ReadOnlyEntity<ID extends Serializable> implements IdentifiedDomainObject<ID> {

	private static final long serialVersionUID = 1L;

	@Id
	private ID id;

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
		ReadOnlyEntity<ID> other = (ReadOnlyEntity<ID>) obj;

		return Objects.equals(getId(), other.getId());
	}
}