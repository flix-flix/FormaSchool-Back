package com.formaschool.back.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formaschool.back.services.CRUDService;

public interface CRUDController<T> {

	public abstract CRUDService<T> getGenericService();

	@GetMapping()
	public default Collection<T> findAll() {
		return getGenericService().findAll();
	}

	@GetMapping("{id}")
	public default T getById(@PathVariable String id) {
		return getGenericService().get(id);
	}

	@PostMapping()
	public default T save(@RequestBody T salon) {
		return getGenericService().save(salon);
	}

	@PatchMapping("{id}")
	public default T update(@RequestBody T salon) {
		return getGenericService().update(salon);
	}

	@DeleteMapping("{id}")
	public default void delete(@PathVariable String id) {
		getGenericService().delete(id);
	}
}
