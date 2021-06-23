package com.formaschool.back._crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		return opt(repo.findById(id));
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

	/** Throw BAD_REQUEST if the Optional is empty */
	protected T opt(Optional<T> opt) {
		return opt.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
	}

	/** Map the entity into the DTO */
	protected <E, DTO> DTO dto(E entity, Class<DTO> cl) {
		return mapper.convertValue(entity, cl);
	}

	/** Map the entity into the DTO */
	protected <DTO> DTO dtoOpt(Optional<T> opt, Class<DTO> cl) {
		return dto(opt(opt), cl);
	}
}
