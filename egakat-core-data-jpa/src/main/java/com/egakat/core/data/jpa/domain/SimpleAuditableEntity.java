package com.egakat.core.data.jpa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.egakat.core.data.jpa.domain.BaseEntity;
import com.egakat.core.domain.VersionableObject;

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
public abstract class SimpleAuditableEntity<ID> extends BaseEntity<ID> implements VersionableObject {

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
}