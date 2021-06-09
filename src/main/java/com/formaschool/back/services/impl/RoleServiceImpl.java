package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.roles.RoleWithoutRights;
import com.formaschool.back.models.Role;
import com.formaschool.back.repositories.RoleRepository;
import com.formaschool.back.services.RoleService;

public class RoleServiceImpl extends CRUDServiceImpl<Role> implements RoleService {

	private RoleRepository repo;

	public RoleServiceImpl(RoleRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}

	public List<RoleWithoutRights> findAllWithoutRights() {
		return repo.findAll().stream().map(role -> dto(role,RoleWithoutRights.class)).collect(Collectors.toList());
	}
}
