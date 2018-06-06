package com.egakat.commons.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.egakat.core.domain.IdentifiedDomainObject;
import com.egakat.core.domain.ObjectAuditableByTime;
import com.egakat.core.domain.ObjectAuditableByUser;
import com.egakat.core.domain.VersionableObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID extends Serializable>
		implements IdentifiedDomainObject<ID>, VersionableObject, ObjectAuditableByUser, ObjectAuditableByTime {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.PRIVATE)
	private ID id;

	@Version
	private int version;

	@Column(name = "fecha_creacion", updatable = false)
	@CreatedDate
	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaCreacion;

	@Column(name = "usuario_creacion", updatable = false)
	@CreatedBy
	private String creadoPor;

	@Column(name = "fecha_modificacion")
	@LastModifiedDate
	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaModificacion;

	@Column(name = "usuario_modificacion")
	@LastModifiedBy
	private String modificadoPor;

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
