package com.formaschool.back.services.impl;

import java.util.List;

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

	@Override
	public void deleteByRoleId(String roleId) {
		List<Permission> permissions = this.repository.findByRoleId(roleId);
		for (Permission permission : permissions) {
			this.repository.deleteById(permission.getId());
		}
	}
}
