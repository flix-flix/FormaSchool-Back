package com.formaschool.back.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.formaschool.back.dto.roles.RoleWithoutRights;
import com.formaschool.back.dto.team.TeamNameDescFile;
import com.formaschool.back.dto.team.TeamNameDescPict;
import com.formaschool.back.dto.team.TeamNameDescPictUpdate;
import com.formaschool.back.dto.team.TeamNamePict;
import com.formaschool.back.models.Role;
import com.formaschool.back.models.Team;

public interface TeamService extends CRUDService<Team> {
	public TeamNameDescPict findTeamNameDescPicById(String id);

	public TeamNameDescPict updateTeamNameDescPic(TeamNameDescPictUpdate dto);

	public List<TeamNamePict> findAllTeamOfUser(String id);

	public TeamNamePict findTeamNamePictById(String id);

	/**
	 * Return all role which are inside the team without their rights
	 * 
	 * @param teamId the id of the team
	 * @return a list of RoleWithoutRights
	 */
	public List<RoleWithoutRights> findRoleWithoutRightsByTeamId(String teamId);
	
	public Team saveWithFile(TeamNameDescFile dto);
	
	public void addRoleToTeam(String teamId, Role role);
	
	public void deleteRole(String teamId, String roleId);
	
	public List<TeamNamePict> findAllTeamNamePict();
	
	public Team findTeamIdBySalonId(String salonId);
}
