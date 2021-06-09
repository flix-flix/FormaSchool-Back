package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.team.TeamNameDescPicDTO;
import com.formaschool.back.dto.team.TeamNamePict;
import com.formaschool.back.dto.team.UpdateTeamNameDescPicDTO;
import com.formaschool.back.models.Team;

public interface TeamService extends CRUDService<Team> {
	public TeamNameDescPicDTO findTeamNameDescPicDtoById(String id);

	public TeamNameDescPicDTO updateTeamNameDescPicDto(UpdateTeamNameDescPicDTO dto);

	public List<TeamNamePict> getAllTeamOfUser(String id);
}
