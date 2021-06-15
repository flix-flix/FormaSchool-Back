package com.formaschool.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.TeamSalonRights;
import com.formaschool.back.repositories.TeamSalonRightsRepository;
import com.formaschool.back.services.TeamSalonRightsService;

public class TeamSalonRightsServiceImpl extends CRUDServiceImpl<TeamSalonRights> implements TeamSalonRightsService {

	// private TeamSalonRightsRepository repo;

	public TeamSalonRightsServiceImpl(TeamSalonRightsRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		// this.repo = repo;
	}
}
