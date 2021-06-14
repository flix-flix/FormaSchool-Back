package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.roles.RoleWithoutRights;
import com.formaschool.back.dto.team.TeamNameDescPict;
import com.formaschool.back.dto.team.TeamNamePict;
import com.formaschool.back.dto.team.TeamNameDescPictUpdate;
import com.formaschool.back.models.Role;
import com.formaschool.back.models.Team;
import com.formaschool.back.repositories.TeamRepository;
import com.formaschool.back.services.MemberService;
import com.formaschool.back.services.TeamService;

public class TeamServiceImpl extends CRUDServiceImpl<Team> implements TeamService {

	private TeamRepository repo;
	private MemberService memberService;

	public TeamServiceImpl(TeamRepository repo, ObjectMapper mapper, MemberService memberService) {
		super(repo, mapper);
		this.repo = repo;
		this.memberService = memberService;
	}

	@Override
	public TeamNameDescPict findTeamNameDescPicDtoById(String id) {
		Team team = this.repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return mapper.convertValue(team, TeamNameDescPict.class);
	}

	@Override
	public TeamNameDescPict updateTeamNameDescPicDto(TeamNameDescPictUpdate dto) {
		Team team = this.repo.findById(dto.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (dto.getName() != null)
			team.setName(dto.getName());
		if (dto.getDesc() != null)
			team.setDesc(dto.getDesc());
		if (dto.getPicture() != null)
			team.setPicture(dto.getPicture());
		Team result = this.repo.save(team);
		return this.mapper.convertValue(result, TeamNameDescPict.class);
	}

	@Override
	public List<TeamNamePict> findAllTeamOfUser(String userId) {
		return memberService.findAllByUserId(userId).stream().map(member -> dto(member.getTeam(), TeamNamePict.class))
				.collect(Collectors.toList());
	}

	@Override
	public TeamNamePict findTeamNamePictById(String id) {
		return dtoOpt(repo.findById(id), TeamNamePict.class);
	}
	// =============================================================================================

	@Override
	public List<RoleWithoutRights> findRoleWithoutRightsByTeamId(String teamId) {
		Team team = this.repo.findById(teamId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return team.getRoles().stream().map(role -> dto(role, RoleWithoutRights.class)).collect(Collectors.toList());
	}

	@Override
	public void addRoleToTeam(String teamId, Role role) {
		Team team = this.repo.findById(teamId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
		team.getRoles().add(role);
		this.repo.save(team);
	}

	@Override
	public void deleteRole(String teamId, String roleId) {
		Team team = this.repo.findById(teamId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
		team.getRoles().stream().filter(role -> role.getId() == roleId).map(role -> team.getRoles().remove(role));	
	}

	@Override
	public List<TeamNamePict> findAllTeamNamePict() {
		List<Team> teams = this.repo.findAll();
		return teams.stream().map(team -> dto(team,TeamNamePict.class)).collect(Collectors.toList());
	}
}
