package com.formaschool.back.services;

import java.util.Collection;

import com.formaschool.back.dto.TeamNameDescPicDTO;

public interface CRUDService<T> {
	public Collection<T> findAll();

	public T get(String id);

	public T save(T obj);

	public T update(T obj);

	public void delete(String id);

	
}
