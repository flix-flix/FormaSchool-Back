package com.formaschool.back.teams;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.teams.dto.TeamNameDescFile;
import com.formaschool.back.teams.dto.TeamNameDescPict;
import com.formaschool.back.teams.dto.TeamNameDescPictUpdate;
import com.formaschool.back.teams.dto.TeamNamePict;
import com.formaschool.back.teams.services.TeamMemberService;
import com.formaschool.back.teams.services.TeamService;

@RestController
@RequestMapping("teams")
@CrossOrigin
public class TeamController implements CRUDController<Team> {

	@Autowired
	private TeamService service;
	@Autowired
	private TeamMemberService teamMemberService;

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
		return teamMemberService.findAllTeamOfUser(userId);
	}

	@GetMapping("teamNamePict")
	public List<TeamNamePict> findAllTeamNamePict() {
		return this.service.findAllTeamNamePict();
	}

	@PostMapping("saveWithFile")
	public Team saveWithFile(@RequestHeader("Authorization") String authorization, @RequestBody TeamNameDescFile team) {
		String userId = authorization.split(" ")[1];
		return service.saveWithFile(team, userId);
	}

	@PatchMapping("teamNameDescPict")
	public TeamNameDescPict updateTeamNameDescPic(@RequestHeader("Authorization") String authorization,
			@RequestBody TeamNameDescPictUpdate dto) {
		String userId = authorization.split(" ")[1];
		return this.service.updateTeamNameDescPic(dto, userId);
	}
}
