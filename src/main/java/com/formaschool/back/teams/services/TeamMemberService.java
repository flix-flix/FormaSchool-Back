package com.formaschool.back.teams.services;

import java.util.List;

import com.formaschool.back.teams.dto.TeamNamePict;

public interface TeamMemberService {

	public List<TeamNamePict> findAllTeamOfUser(String id);
}
