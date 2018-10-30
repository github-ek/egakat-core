package com.egakat.core.services.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.OptimisticLockException;

import com.egakat.core.data.jpa.repository.IdentifiedDomainObjectRepository;
import com.egakat.core.domain.IdentifiedDomainObject;
import com.egakat.core.domain.VersionableObject;
import com.egakat.core.services.crud.api.CrudService;

import lombok.val;

abstract public class CrudServiceImpl<E extends IdentifiedDomainObject<ID>, M extends IdentifiedDomainObject<ID>, ID>
		extends QueryServiceImpl<E, M, ID> implements CrudService<M, ID> {

	@Override
	abstract protected IdentifiedDomainObjectRepository<E, ID> getRepository();

	// -----------------------------------------------------------------------------------------------------------------------
	// CREATE
	// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public M create(M model) {
		E entity = newEntity();

		entity = mergeEntity(model, entity);
		entity = beforeCreate(entity);
		entity = saveAndFlush(entity);

		val result = asModel(entity);
		return result;
	}

	@Override
	public List<M> create(List<M> models) {
		val result = new ArrayList<M>();

		for (M m : models) {
			result.add(create(m));
		}

		return result;
	}

	protected E beforeCreate(E entity) {
		return entity;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	// UPDATE
	// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public M update(M model) {
		E entity = findOneEntityById(model.getId());

		entity = mergeEntity(model, entity);
		entity = beforeUpdate(entity);

		setVersion(model, entity);
		entity = saveAndFlush(entity);

		M result = asModel(entity);
		return result;
	}

	protected void setVersion(M model, E entity) {
		if (entity instanceof VersionableObject && model instanceof VersionableObject) {
			val e = (VersionableObject) entity;
			val m = (VersionableObject) model;
			e.setVersion(m.getVersion());
		}
	}

	@Override
	public List<M> update(List<M> models) {
		val result = new ArrayList<M>();

		for (M m : models) {
			result.add(update(m));
		}

		return result;
	}

	protected E beforeUpdate(E entity) {
		return entity;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	// DELETE
	// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public void delete(ID id) {
		E entity = findOneEntityById(id);

		if (entity instanceof VersionableObject) {
			throw new UnsupportedOperationException(
					"La entidad implementa la interfaz VersionableObject y debe ser eliminada por medio del metodo delete(ID id, int version)");
		}

		entity = beforeDelete(entity);
		deleteEntity(entity);
	}

	@Override
	public void delete(List<ID> ids) {
		for (val e : ids) {
			delete(e);
		}
	}

	@Override
	public void delete(ID id, int version) {
		E entity = findOneEntityById(id);

		if (!(entity instanceof VersionableObject)) {
			throw new UnsupportedOperationException(
					"La entidad NO implementa la interfaz VersionableObject y debe ser eliminada por medio del metodo delete(ID id)");
		}

		val e = (VersionableObject) entity;
		if (e.getVersion() != version) {
			throw new OptimisticLockException();
		}

		entity = beforeDelete(entity);
		deleteEntity(entity);
	}

	@Override
	public void delete(Map<ID, Integer> models) {
		for (val e : models.entrySet()) {
			delete(e.getKey(), e.getValue());
		}
	}

	protected E beforeDelete(E entity) {
		return entity;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	// Entities
	// -----------------------------------------------------------------------------------------------------------------------
	abstract protected E newEntity();

	abstract protected E mergeEntity(M model, E entity);

	protected E saveAndFlush(E entity) {
		val result = getRepository().saveAndFlush(entity);
		return result;
	}

	protected void deleteEntity(E entity) {
		getRepository().delete(entity);
	}
}
