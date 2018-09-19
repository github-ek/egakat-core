package com.egakat.commons.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.egakat.core.data.jpa.domain.BaseEntity;
import com.egakat.core.domain.ObjectAuditableByTime;
import com.egakat.core.domain.VersionableObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class SimpleAuditableEntity<ID> extends BaseEntity<ID> implements VersionableObject, ObjectAuditableByTime {

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

	@Column(name = "fecha_modificacion")
	@LastModifiedDate
	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaModificacion;

	public SimpleAuditableEntity(ID id, int version, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
		super();
		this.id = id;
		this.version = version;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}
}
