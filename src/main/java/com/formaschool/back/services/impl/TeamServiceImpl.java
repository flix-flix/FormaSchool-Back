package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.team.TeamNameDescPicDTO;
import com.formaschool.back.dto.team.TeamNamePict;
import com.formaschool.back.dto.team.UpdateTeamNameDescPicDTO;
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
	public TeamNameDescPicDTO findTeamNameDescPicDtoById(String id) {
		Team team = this.repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return mapper.convertValue(team, TeamNameDescPicDTO.class);
	}

	@Override
	public TeamNameDescPicDTO updateTeamNameDescPicDto(UpdateTeamNameDescPicDTO dto) {
		Team team = this.repo.findById(dto.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (dto.getName() != null)
			team.setName(dto.getName());
		if (dto.getDesc() != null)
			team.setDesc(dto.getDesc());
		if (dto.getPicture() != null)
			team.setPicture(dto.getPicture());
		Team result = this.repo.save(team);
		return this.mapper.convertValue(result, TeamNameDescPicDTO.class);
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
}
