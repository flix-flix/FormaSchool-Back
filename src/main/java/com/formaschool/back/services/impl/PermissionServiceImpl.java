package com.formaschool.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.Permission;
import com.formaschool.back.repositories.PermissionRepository;
import com.formaschool.back.services.PermissionService;

public class PermissionServiceImpl extends CRUDServiceImpl<Permission> implements PermissionService {

	private PermissionRepository repository;

	public PermissionServiceImpl(PermissionRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}
}
