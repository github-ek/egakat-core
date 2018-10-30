package com.anexa.core.alertas.service.api;

public interface AlertService<R, M> {

	M getMessage(R request);

	M getMessage(R request, Throwable t);
}