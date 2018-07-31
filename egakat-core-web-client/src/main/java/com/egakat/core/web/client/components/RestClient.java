package com.egakat.core.web.client.components;

import org.springframework.http.ResponseEntity;

public interface RestClient {

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- HTTP GET METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	<T, ID> ResponseEntity<T> get(String resourcePath, Class<T> responseType, ID id);

	<T> ResponseEntity<T> getOneQuery(String resourcePath, String query, Class<T> responseType, Object... uriVariables);

	<T> ResponseEntity<T[]> getAllQuery(String resourcePath, String query, Class<T[]> responseType, Object... uriVariables);

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- HTTP POST, PUT, DELETE METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	<T> ResponseEntity<T> post(String resourcePath, Object model, Class<T> responseType, Object... uriVariables);

	<T> ResponseEntity<T> put(String resourcePath, Object model, Class<T> responseType, Object... uriVariables);

	<ID > void delete(String resourcePath, ID id);
	
	<ID > void delete(String resourcePath, ID id, int version);
}