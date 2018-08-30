package com.egakat.core.web.client.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.val;

@Component
public class RestClientImpl implements RestClient {
	
	@Autowired
	private RestTemplate restTemplate;

	protected RestTemplate getRestTemplate() {
		return restTemplate;
	};

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- HTTP GET METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public <T, ID> ResponseEntity<T> get(String resourcePath, Class<T> responseType, ID id) {
		val query = "{id}";
		val result = getOneQuery(resourcePath, query, responseType, id);
		return result;
	}

	@Override
	public <T> ResponseEntity<T> getOneQuery(String resourcePath, String query, Class<T> responseType,
			Object... uriVariables) {
		val url = buildUrl(resourcePath, query);
		val request = createRequestEntity("");
		val result = getRestTemplate().exchange(url, HttpMethod.GET, request, responseType, uriVariables);
		return result;
	}

	@Override
	public <T> ResponseEntity<T[]> getAllQuery(String resourcePath, String query, Class<T[]> responseType,
			Object... uriVariables) {
		val url = buildUrl(resourcePath, query);
		val request = createRequestEntity("");
		val result = getRestTemplate().exchange(url, HttpMethod.GET, request, responseType, uriVariables);
		return result;
	}

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- HTTP POST, PUT, DELETE METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public <T> ResponseEntity<T> post(String resourcePath, Object model, Class<T> responseType,
			Object... uriVariables) {
		val request = createRequestEntity(mapModelToRequestBody(model));
		val result = getRestTemplate().exchange(resourcePath, HttpMethod.POST, request, responseType, uriVariables);
		return result;
	}

	@Override
	public <T> ResponseEntity<T> put(String resourcePath, Object model, Class<T> responseType, Object... uriVariables) {
		val request = createRequestEntity(mapModelToRequestBody(model));
		val result = getRestTemplate().exchange(resourcePath, HttpMethod.PUT, request, responseType, uriVariables);
		return result;
	}

	@Override
	public <ID> void delete(String resourcePath, ID id) {
		getRestTemplate().delete(resourcePath, id);
	}

	@Override
	public <ID> void delete(String resourcePath, ID id, int version) {
		getRestTemplate().delete(resourcePath, id, version);
	}

	protected <T> Object mapModelToRequestBody(T model) {
		return model;
	}

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// --
	// ------------------------------------------------------------------------------------------------------------------------------------

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

	protected String buildUrl(String resourcePath, String query) {
		val sb = new StringBuilder();
		sb.append(resourcePath);

		query = query.trim();

		if (!query.isEmpty()) {
			char c = query.charAt(0);
			if (c != '?' && c != '/') {
				sb.append('/');
			}
			sb.append(query);
		}

		return sb.toString();
	}
}
