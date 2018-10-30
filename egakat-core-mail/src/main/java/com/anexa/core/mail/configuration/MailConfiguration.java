package com.anexa.core.mail.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.val;


@Configuration
public class MailConfiguration {

	public MailConfiguration() {
		super();
	}

	@Bean
	@ConfigurationProperties(prefix = "mail")
	public MailProperties mailProperties() {
		val result = new MailProperties();
		return result;
	}

}