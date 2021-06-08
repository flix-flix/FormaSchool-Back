package com.formaschool.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.Team;
import com.formaschool.back.repositories.TeamRepository;
import com.formaschool.back.services.TeamService;

public class TeamServiceImpl extends CRUDServiceImpl<Team> implements TeamService {
	private TeamRepository repo;

	public TeamServiceImpl(TeamRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}
}
