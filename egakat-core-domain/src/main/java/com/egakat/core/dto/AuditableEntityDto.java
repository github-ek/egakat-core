package com.egakat.core.dto;

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
public abstract class AuditableEntityDto<ID> extends SimpleAuditableEntityDto<ID> implements ObjectAuditableByUser {

	@NotNull
	@Size(max = 50)
	private String creadoPor;

	@NotNull
	@Size(max = 50)
	private String modificadoPor;
}