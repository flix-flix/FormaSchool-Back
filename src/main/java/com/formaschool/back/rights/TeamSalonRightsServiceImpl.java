package com.formaschool.back.rights;

import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back._utils.Utils;

public class TeamSalonRightsServiceImpl extends CRUDServiceImpl<TeamSalonRights> implements TeamSalonRightsService {

	// private TeamSalonRightsRepository repo;

	public TeamSalonRightsServiceImpl(TeamSalonRightsRepository repo, Utils utils) {
		super(repo, utils);
		// this.repo = repo;
	}
}
