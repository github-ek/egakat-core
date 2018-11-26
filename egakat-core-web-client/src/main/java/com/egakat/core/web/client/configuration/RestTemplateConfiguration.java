package com.egakat.core.web.client.configuration;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.val;

public abstract class RestTemplateConfiguration {

	protected CloseableHttpClient getHttpClient() {
		int timeout = 60 * 1000;
		// @formatter:off
		RequestConfig config = RequestConfig
				.custom()
				.setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout)
				.build();
		// @formatter:on
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		return client;
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
