package com.formaschool.back.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.TeamNameDescPicDTO;
import com.formaschool.back.models.Team;
import com.formaschool.back.repositories.TeamRepository;
import com.formaschool.back.services.TeamService;

public class TeamServiceImpl extends CRUDServiceImpl<Team> implements TeamService {
	
	private ObjectMapper mapper;
		
	private TeamRepository repository;
		
	public TeamServiceImpl(TeamRepository repository, ObjectMapper mapperParam) {
		this.repository = repository;
		this.mapper = mapperParam;
	}

	@Override
	public TeamNameDescPicDTO findTeamNameDescPicDtoById(String id) {
		Team team = this.repository.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		return mapper.convertValue(team, TeamNameDescPicDTO.class);
	}
}
