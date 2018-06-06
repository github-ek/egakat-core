package com.egakat.commons.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.egakat.core.domain.IdentifiedDomainObject;
import com.egakat.core.domain.ObjectAuditableByTime;
import com.egakat.core.domain.ObjectAuditableByUser;
import com.egakat.core.domain.VersionableObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntityDto<ID extends Serializable>
		implements IdentifiedDomainObject<ID>, VersionableObject, ObjectAuditableByTime, ObjectAuditableByUser {

	private static final long serialVersionUID = 1L;

	private ID id;
	
	@NumberFormat
	private int version;

	@NotNull
	@Size(max = 50)
	private String creadoPor;

	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaCreacion;

	@NotNull
	@Size(max = 50)
	private String modificadoPor;

	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaModificacion;

}
