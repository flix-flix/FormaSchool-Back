package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.log.LogWithoutId;
import com.formaschool.back.models.Log;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.LogService;

@RestController
@RequestMapping("logs")
@CrossOrigin
public class LogController implements CRUDController<Log> {
	
	@Autowired
	private LogService service;

	@Override
	public CRUDService<Log> getGenericService() {
		return service;
	}
	
	@GetMapping("withoutId")
	public List<LogWithoutId> findAllWithoutId(){
		return this.service.findAllWithoutId();
	}
	
	@GetMapping("withoutId/{teamId}")
	public List<LogWithoutId> findWithoutIdByTeamId(@PathVariable String teamId){
		System.out.println(teamId);
		return this.service.findWithoutIdByTeam(teamId);
	}

}
