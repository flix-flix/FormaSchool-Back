package com.formaschool.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.models.TeamSalonRights;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.TeamSalonRightsService;

@CrossOrigin
@RestController
@RequestMapping("teamSalonRights")
public class TeamSalonRightsController implements CRUDController<TeamSalonRights>{

	@Autowired
	private TeamSalonRightsService service;
	
	@Override
	public CRUDService<TeamSalonRights> getGenericService() {
		return service;
	}
}
