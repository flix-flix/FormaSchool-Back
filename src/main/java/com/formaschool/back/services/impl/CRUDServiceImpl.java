package com.formaschool.back.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.services.CRUDService;

public class CRUDServiceImpl<T> implements CRUDService<T> {

	private MongoRepository<T, String> repo;
	protected ObjectMapper mapper;

	public CRUDServiceImpl(MongoRepository<T, String> repo, ObjectMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public List<T> findAll() {
		return repo.findAll();
	}

	@Override
	public T get(String id) {
		return repo.findById(id).get();
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

	/** Throw NOT_FOUND if the Optional is empty */
	protected T get(Optional<T> opt) {
		return opt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	protected <DTO> DTO map(Optional<T> opt, Class<DTO> cl) {
		return mapper.convertValue(get(opt), cl);
	}
}
