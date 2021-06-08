package com.formaschool.back.services;

import com.formaschool.back.dto.team.TeamNameDescPicDTO;
import com.formaschool.back.models.Team;

public interface TeamService extends CRUDService<Team> {
	public TeamNameDescPicDTO findTeamNameDescPicDtoById(String id);
}
