package com.formaschool.back.teams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back.files.FileService;
import com.formaschool.back.files.Folder;
import com.formaschool.back.roles.Role;
import com.formaschool.back.roles.dto.RoleWithoutRights;
import com.formaschool.back.salons.SalonService;
import com.formaschool.back.teams.dto.TeamNameDescFile;
import com.formaschool.back.teams.dto.TeamNameDescPict;
import com.formaschool.back.teams.dto.TeamNameDescPictUpdate;
import com.formaschool.back.teams.dto.TeamNamePict;

public class TeamServiceImpl extends CRUDServiceImpl<Team> implements TeamService {

	private TeamRepository repo;
	private SalonService salonService;
	private FileService fileService;

	public TeamServiceImpl(TeamRepository repo, ObjectMapper mapper, SalonService salonService,
			FileService fileService) {
		super(repo, mapper);
		this.repo = repo;
		this.salonService = salonService;
		this.fileService = fileService;
	}

	@Override
	public TeamNameDescPict findTeamNameDescPicById(String id) {
		Team team = this.repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return mapper.convertValue(team, TeamNameDescPict.class);
	}

	@Override
	public TeamNameDescPict updateTeamNameDescPic(TeamNameDescPictUpdate dto) {
		Team team = this.repo.findById(dto.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (dto.getName() != null)
			team.setName(dto.getName());
		if (dto.getDesc() != null)
			team.setDesc(dto.getDesc());

		// TODO
		// if (dto.getPicture() != null)
		// team.setPicture(dto.getPicture());

		Team result = this.repo.save(team);
		return this.mapper.convertValue(result, TeamNameDescPict.class);
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
		return teams.stream().map(team -> dto(team, TeamNamePict.class)).collect(Collectors.toList());
	}

	@Override
	public Team findTeamIdBySalonId(String salonId) {
		return this.salonService.get(salonId).getTeam();
	}

	@Override
	public Team saveWithFile(TeamNameDescFile team) {
		Team entity;
		if (team.getFile() != null) {
			entity = new Team(team.getName(), team.getDesc(),
					fileService.upload(Folder.TEAMS, team.getFilename(), team.getFile()), new ArrayList<>());
		} else {
			entity = new Team(team.getName(), team.getDesc(), null, new ArrayList<>());
		}
		return repo.save(entity);
	}
}
