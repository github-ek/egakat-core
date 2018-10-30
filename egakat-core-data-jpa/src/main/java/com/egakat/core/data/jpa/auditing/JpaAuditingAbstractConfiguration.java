package com.egakat.core.data.jpa.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public abstract class JpaAuditingAbstractConfiguration {
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
}