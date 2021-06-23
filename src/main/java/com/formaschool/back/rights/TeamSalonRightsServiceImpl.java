package com.formaschool.back.rights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;

public class TeamSalonRightsServiceImpl extends CRUDServiceImpl<TeamSalonRights> implements TeamSalonRightsService {

	// private TeamSalonRightsRepository repo;

	public TeamSalonRightsServiceImpl(TeamSalonRightsRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		// this.repo = repo;
	}
}
