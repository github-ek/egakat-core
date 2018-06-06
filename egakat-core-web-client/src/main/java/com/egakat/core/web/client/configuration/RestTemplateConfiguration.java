package com.egakat.core.web.client.configuration;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.val;

public abstract class RestTemplateConfiguration {

	protected CloseableHttpClient getHttpClient() {
		return HttpClientBuilder.create().build();
	}

	@Bean
	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
		val httpClient = getHttpClient();

		val result = new HttpComponentsClientHttpRequestFactory(httpClient);
		return result;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder,
			HttpComponentsClientHttpRequestFactory requestFactory) {
		val result = builder.build();

		result.setRequestFactory(requestFactory);
		return result;
	}
}
