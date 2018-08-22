package com.egakat.core.data.jpa.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
public abstract class ReadOnlyEntity<ID> extends BaseEntity<ID> {

	@Id
	private ID id;

}