package com.egakat.commons.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.egakat.core.domain.ObjectAuditableByUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class BusinessEntityDto<ID> extends SimpleAuditableEntityDto<ID> implements ObjectAuditableByUser {

	@NotNull
	@Size(max = 50)
	private String creadoPor;

	@NotNull
	@Size(max = 50)
	private String modificadoPor;

	public BusinessEntityDto(ID id, int version, LocalDateTime fechaCreacion, @NotNull @Size(max = 50) String creadoPor,
			LocalDateTime fechaModificacion, @NotNull @Size(max = 50) String modificadoPor) {
		super(id, version, fechaCreacion, fechaModificacion);
		this.creadoPor = creadoPor;
		this.modificadoPor = modificadoPor;
	}
}
