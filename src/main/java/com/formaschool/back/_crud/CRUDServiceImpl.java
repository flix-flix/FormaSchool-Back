package com.formaschool.back._crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back._utils.Utils;

public class CRUDServiceImpl<T> implements CRUDService<T> {

	private MongoRepository<T, String> repo;
	private Utils utils;

	public CRUDServiceImpl(MongoRepository<T, String> repo, Utils utils) {
		this.repo = repo;
		this.utils = utils;
	}

	// ====================================================================================================

	@Override
	public List<T> findAll() {
		return repo.findAll();
	}

	@Override
	public T get(String id) {
		return utils.opt(repo.findById(id));
	}

	@Override
	public T save(T obj) {
		return repo.save(obj);
	}

	@Override
	public T update(T obj) {
		return repo.save(obj);
	}

	@Override
	public void delete(String id) {
		repo.deleteById(id);
	}

	// ====================================================================================================

	public T opt(Optional<T> opt) {
		return utils.opt(opt);
	}

	public <E, DTO> DTO dto(E entity, Class<DTO> cl) {
		return utils.dto(entity, cl);
	}
}
