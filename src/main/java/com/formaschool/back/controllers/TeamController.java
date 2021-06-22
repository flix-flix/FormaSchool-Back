package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formaschool.back.dto.team.TeamNameDescFile;
import com.formaschool.back.dto.team.TeamNameDescPict;
import com.formaschool.back.dto.team.TeamNameDescPictUpdate;
import com.formaschool.back.dto.team.TeamNamePict;
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


	@GetMapping("bySalon/{salonId}")
	public Team findTeamIdBySalonId(@PathVariable String salonId) {
		return this.service.findTeamIdBySalonId(salonId);
	}
	
	@GetMapping("teamDesc/{teamId}")
	public TeamNameDescPict findById(@PathVariable String teamId) {
		return this.service.findTeamNameDescPicById(teamId);
	}

	@GetMapping("teamNamePict/{teamId}")
	public TeamNamePict findNamePictById(@PathVariable String teamId) {
		return this.service.findTeamNamePictById(teamId);
	}
	
	@GetMapping("teamNameDescPict/{teamId}")
	public TeamNameDescPict findNameDescPic(@PathVariable String teamId) {
		return this.service.findTeamNameDescPicById(teamId);
	}

	@GetMapping("ofUser/{userId}")
	public List<TeamNamePict> findTeamsOfUser(@PathVariable String userId) {
		return service.findAllTeamOfUser(userId);
	}

	@GetMapping("teamNamePict")
	public List<TeamNamePict> findAllTeamNamePict() {
		return this.service.findAllTeamNamePict();
	}
	@PostMapping("saveWithFile")
	public Team saveWithFile(@RequestBody TeamNameDescFile team) {
		return service.saveWithFile(team);
	}
	@PatchMapping("teamNameDescPict/{teamId}")
	public TeamNameDescPict updateTeamNameDescPic(@RequestBody TeamNameDescPictUpdate dto) {
		return this.service.updateTeamNameDescPic(dto);
	}
}
