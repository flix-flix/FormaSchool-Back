package com.formaschool.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.TeamNameDescPicDTO;
import com.formaschool.back.models.Team;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.TeamService;

@RestController
@RequestMapping("teams")
@CrossOrigin
public class TeamController implements CRUDController<Team> {
	
	@Autowired
	private TeamService service;

	@Override
	public CRUDService<Team> getGenericService() {
		return service;
	}
	
	@GetMapping("teamDesc/{id}")
	public TeamNameDescPicDTO findById(@PathVariable String id) {
		return this.service.findTeamNameDescPicDtoById(id);
	}
}
