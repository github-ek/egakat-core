package com.egakat.commons.domain.identity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.egakat.commons.domain.SimpleAuditableEntity;
import com.egakat.core.domain.ObjectAuditableByTime;
import com.egakat.core.domain.VersionableObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class SimpleAuditableEntityWithIdentity<ID> extends SimpleAuditableEntity<ID> implements VersionableObject, ObjectAuditableByTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.PRIVATE)
	private ID id;
}
