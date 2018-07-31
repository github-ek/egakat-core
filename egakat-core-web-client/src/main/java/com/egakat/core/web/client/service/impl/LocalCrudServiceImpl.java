package com.egakat.core.web.client.service.impl;

import com.egakat.core.web.client.service.api.LocalCrudService;

import lombok.val;

public abstract class LocalCrudServiceImpl<M, ID> extends LocalQueryServiceImpl<M, ID> 
		implements LocalCrudService<M, ID> {

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// --
	// ------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public M create(M model) {
		val url = getResourcePath(getProperties().getBasePath(), getResourceName());
		val response = getRestClient().post(url, model, getResponseType());
		val result = response.getBody();
		return result;
	}

	@Override
	public M update(M model) {
		val url = getResourcePath(getProperties().getBasePath(), getResourceName());
		val response = getRestClient().put(url, model, getResponseType());
		val result = response.getBody();
		return result;
	}

	@Override
	public void delete(ID id) {
		String url = getResourcePath() + "/{id}";
		getRestClient().delete(url, id);
	}

	@Override
	public void delete(ID id, int version) {
		String url = getResourcePath() + "/{id}?version={version}";
		getRestClient().delete(url, id, version);
	}
}
