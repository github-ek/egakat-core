package com.egakat.core.web.api.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.egakat.core.domain.IdentifiedDomainObject;
import com.egakat.core.services.crud.api.CrudService;

abstract public class CrudRestController<M extends IdentifiedDomainObject<ID>, ID> extends QueryRestController<M, ID> {

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- PATHS
	// ------------------------------------------------------------------------------------------------------------------------------------
	protected static final String PATH_DELETE = "/{id}/{version}";

	public CrudRestController() {
		super();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// -- Servicio
	// ------------------------------------------------------------------------------------------------------------------------------------
	@Override
	abstract protected CrudService<M, ID> getService();

	// -------------------------------------------------------------------------------------------------------------------------------------
	// -- HTTP POST, PUT, DELETE METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody M model, BindingResult bindingResult) {
		try {
			if (model.getId() != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}

			if (bindingResult.hasErrors()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
			}

			M result = getService().create(model);

			return ResponseEntity.created(showURI(result).toUri()).body(result);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e);
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody M model, BindingResult bindingResult) {
		try {
			if (model.getId() == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}

			if (bindingResult.hasErrors()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
			}

			M result = getService().update(model);

			return ResponseEntity.created(showURI(result).toUri()).body(result);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e);
		}
	}

	@DeleteMapping(PATH_ENTITY)
	public ResponseEntity<?> delete(@PathVariable ID id) {
		try {
			getService().delete(id);

			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e);
		}
	}

	@DeleteMapping(PATH_DELETE)
	public ResponseEntity<?> delete(@PathVariable ID id, @PathVariable Integer version) {
		try {
			getService().delete(id, version);

			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e);
		}
	}
}