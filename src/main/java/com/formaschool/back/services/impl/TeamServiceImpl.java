package com.formaschool.back.services.impl;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.salon.SalonNameDescDTO;
import com.formaschool.back.dto.salon.UpdateSalonNameDescDTO;
import com.formaschool.back.dto.team.TeamNameDescPicDTO;
import com.formaschool.back.dto.team.UpdateTeamNameDescPicDTO;
import com.formaschool.back.models.Salon;
import com.formaschool.back.models.Team;
import com.formaschool.back.repositories.TeamRepository;
import com.formaschool.back.services.TeamService;

public class TeamServiceImpl extends CRUDServiceImpl<Team> implements TeamService {
	
    private TeamRepository repo;
		
	public TeamServiceImpl(TeamRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}
	
	@Override
	public TeamNameDescPicDTO findTeamNameDescPicDtoById(String id) {
		Team team = this.repo.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		return mapper.convertValue(team, TeamNameDescPicDTO.class);
	}

	@Override
	public TeamNameDescPicDTO updateTeamNameDescPicDto(UpdateTeamNameDescPicDTO dto) {
		Team team= this.repo.findById(dto.getId())
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(dto.getName()!=null)
			team.setName(dto.getName());
		if(dto.getDesc() != null)
			team.setDesc(dto.getDesc());
		if(dto.getPicture() != null)
			team.setPicture(dto.getPicture());
		Team result = this.repo.save(team);
		return this.mapper.convertValue(result, TeamNameDescPicDTO.class);
	}
	
}

