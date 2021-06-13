package com.formaschool.back.services.impl;

import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.Permission;
import com.formaschool.back.repositories.PermissionRepository;
import com.formaschool.back.services.PermissionService;
import com.formaschool.back.services.TeamSalonRightsService;

public class PermissionServiceImpl extends CRUDServiceImpl<Permission> implements PermissionService {

	private PermissionRepository repository;
	private TeamSalonRightsService tsrService;
	

	public PermissionServiceImpl(PermissionRepository repository, TeamSalonRightsService tsrService, ObjectMapper mapper) {
		super(repository, mapper);
		this.tsrService = tsrService;
		this.repository = repository;
	}

	@Override
	public void deleteByRoleId(String roleId) {
		List<Permission> permissions = this.repository.findByRoleId(roleId);
		for (Permission permission : permissions) {
			if(permission.getCommonRights() != null) {
				this.tsrService.delete(permission.getCommonRights().getId());
			}
			this.repository.deleteById(permission.getId());
		}
		
	}
}
