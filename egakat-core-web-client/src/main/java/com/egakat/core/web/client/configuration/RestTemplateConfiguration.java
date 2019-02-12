package com.egakat.core.web.client.configuration;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.val;

public abstract class RestTemplateConfiguration {

	@Bean
	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
		val httpClient = getHttpClientBuilder().build();

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

	protected HttpClientBuilder getHttpClientBuilder() {
		RequestConfig config = getRequestConfigBuilder().build();

		val result = HttpClientBuilder.create().setDefaultRequestConfig(config)
				.setKeepAliveStrategy(getKeepAliveStrategy());
		return result;
	}

	protected RequestConfig.Builder getRequestConfigBuilder() {
		int timeout = getTimeout();

		// @formatter:off
		val result = RequestConfig.custom()
				.setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout);
		// @formatter:on
		return result;
	}

	protected int getTimeout() {
		return 60 * 1000;
	}

	ConnectionKeepAliveStrategy getKeepAliveStrategy() {
		ConnectionKeepAliveStrategy result = new ConnectionKeepAliveStrategy() {

			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						return Long.parseLong(value) * 1000;
					}
				}
				return 60 * 1000;
			}
		};

		return result;
	}
}
