package com.formaschool.back._crud;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	public default T save(@RequestBody T obj) {
		return getGenericService().save(obj);
	}

	@PatchMapping("{id}")
	public default T update(@RequestBody T obj) {
		return getGenericService().update(obj);
	}

	@DeleteMapping("{id}")
	public default void delete(@PathVariable String id) {
		getGenericService().delete(id);
	}
}
