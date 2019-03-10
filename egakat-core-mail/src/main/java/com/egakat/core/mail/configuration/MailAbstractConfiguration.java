package com.egakat.core.mail.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import lombok.val;

public class MailAbstractConfiguration {

	public MailAbstractConfiguration() {
		super();
	}

	@Bean
	@ConfigurationProperties(prefix = "mail")
	public MailProperties mailProperties() {
		val result = new MailProperties();
		return result;
	}

}