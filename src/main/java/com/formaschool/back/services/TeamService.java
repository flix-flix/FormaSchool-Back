package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.roles.RoleWithoutRights;
import com.formaschool.back.dto.team.TeamNameDescPicDTO;
import com.formaschool.back.dto.team.TeamNamePict;
import com.formaschool.back.dto.team.UpdateTeamNameDescPicDTO;
import com.formaschool.back.models.Role;
import com.formaschool.back.models.Team;

public interface TeamService extends CRUDService<Team> {
	public TeamNameDescPicDTO findTeamNameDescPicDtoById(String id);

	public TeamNameDescPicDTO updateTeamNameDescPicDto(UpdateTeamNameDescPicDTO dto);

	public List<TeamNamePict> findAllTeamOfUser(String id);

	public TeamNamePict findTeamNamePictById(String id);

	/**
	 * Return all role which are inside the team without their rights
	 * 
	 * @param teamId the id of the team
	 * @return a list of RoleWithoutRights
	 */
	public List<RoleWithoutRights> findRoleWithoutRightsByTeamId(String teamId);
	
	public void addRoleToTeam(String teamId, Role role);
	
	public void deleteRole(String teamId, String roleId);
}
