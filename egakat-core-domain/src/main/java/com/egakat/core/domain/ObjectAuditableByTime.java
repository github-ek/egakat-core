package com.egakat.core.domain;

import java.time.LocalDateTime;

public interface ObjectAuditableByTime {

	LocalDateTime getFechaCreacion();

	void setFechaCreacion(LocalDateTime fechaCreacion);

	LocalDateTime getFechaModificacion();

	void setFechaModificacion(LocalDateTime fechaModificacion);
}
