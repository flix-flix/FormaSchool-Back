package com.formaschool.back._crud;

import static com.formaschool.back._utils.Utils.opt;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CRUDServiceImpl<T> implements CRUDService<T> {

	private MongoRepository<T, String> repo;

	public CRUDServiceImpl(MongoRepository<T, String> repo, ObjectMapper mapper) {
		this.repo = repo;
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
}
