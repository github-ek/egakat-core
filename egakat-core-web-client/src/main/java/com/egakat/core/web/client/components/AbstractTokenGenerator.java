package com.egakat.core.web.client.components;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.egakat.core.web.client.properties.RestTokenGeneratorProperties;
import com.egakat.core.web.client.service.api.CacheEvictSupported;

import lombok.val;

public abstract class AbstractTokenGenerator<B,R> implements CacheEvictSupported{

	protected abstract RestTokenGeneratorProperties getProperties();

	protected abstract RestTemplate getRestTemplate();

	public String token() {
		val url = getProperties().getApiTokenUrlGenerator();
		val body = getBodyRequest();
		val entity = createRequestEntity(body);
	
		try {
			val response = login(url, entity);
			val token = getToken(response.getBody());
			return token;
		} catch (HttpServerErrorException | HttpClientErrorException | ResourceAccessException e) {
			throw e;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	protected abstract B getBodyRequest();

	protected abstract ResponseEntity<R> login(String url, HttpEntity<?> entity);

	protected abstract String getToken(R response);

	protected HttpEntity<?> createRequestEntity(Object body) {
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<?> result = new HttpEntity<>(body, headers);
		return result;
	}

	protected HttpHeaders getHttpHeaders() {
		HttpHeaders headers;
		headers = new HttpHeaders();
		headers.setContentType(getMediaType());
		return headers;
	}

	protected MediaType getMediaType() {
		return MediaType.APPLICATION_JSON;
	}
}