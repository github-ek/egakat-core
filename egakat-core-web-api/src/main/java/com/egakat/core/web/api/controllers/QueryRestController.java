package com.egakat.core.web.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.egakat.core.domain.IdentifiedDomainObject;
import com.egakat.core.services.crud.api.QueryService;

import lombok.val;

abstract public class QueryRestController<M extends IdentifiedDomainObject<ID>, ID> {

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- PATHS
	// ------------------------------------------------------------------------------------------------------------------------------------
	protected static final String PATH_LIST = "";

	protected static final String PATH_LIST_SEARCH_IN = "/[{ids}]";

	protected static final String PATH_ENTITY = "/{id}";

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- FORMAT
	// ------------------------------------------------------------------------------------------------------------------------------------
	protected static final String FORMATO_DATE = "yyyy-MM-dd";

	protected static final String FORMATO_TIME = "HH:mm";

	public QueryRestController() {
		super();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// -- Servicio
	// ------------------------------------------------------------------------------------------------------------------------------------
	abstract protected QueryService<M, ID> getService();

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- HTTP GET METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping(PATH_LIST_SEARCH_IN)
	public ResponseEntity<List<M>> list(@PathVariable List<ID> ids) {
		val result = getService().findAllById(ids);
		return ResponseEntity.ok(result);
	}

	@GetMapping(path = PATH_ENTITY)
	public ResponseEntity<M> get(@PathVariable ID id) {
		Optional<M> result = getService().findById(id);

		if (!result.isPresent()) {
			throw new NotFoundException();
		}

		return ResponseEntity.ok(result.get());
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// -- URIs
	// ------------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	protected UriComponents showURI(IdentifiedDomainObject<ID> model) {
		// @formatter:off
		val result = MvcUriComponentsBuilder
				.fromMethodCall(MvcUriComponentsBuilder.on(QueryRestController.class).get(null))
				.buildAndExpand(model.getId())
				.encode();
		// @formatter:on

		return result;
	}
}