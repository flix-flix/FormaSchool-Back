package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.roles.CreateRole;
import com.formaschool.back.dto.roles.RoleWithDescription;
import com.formaschool.back.dto.roles.RoleWithoutRights;
import com.formaschool.back.models.Permission;
import com.formaschool.back.models.Role;
import com.formaschool.back.models.Salon;
import com.formaschool.back.models.TeamSalonRights;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.PermissionService;
import com.formaschool.back.services.RoleService;
import com.formaschool.back.services.SalonService;
import com.formaschool.back.services.TeamSalonRightsService;
import com.formaschool.back.services.TeamService;

@CrossOrigin
@RestController
@RequestMapping("roles")
public class RoleController implements CRUDController<Role> {

	@Autowired
	private RoleService service;

	@Autowired
	private TeamService teamService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private SalonService salonService;

	@Autowired
	private TeamSalonRightsService tsrService;

	@Override
	public CRUDService<Role> getGenericService() {
		return service;
	}

	@GetMapping("withoutRights")
	public List<RoleWithoutRights> findAllWithoutRights() {
		return this.service.findAllWithoutRights();
	}

	@GetMapping("withoutRights/{teamId}")
	public List<RoleWithoutRights> findAllWithoutRightsByTeamId(@PathVariable String teamId) {
		return this.teamService.findRoleWithoutRightsByTeamId(teamId);
	}

	@GetMapping("withDesc/{roleId}")
	public RoleWithDescription findRoleWithDescriptionById(@PathVariable String roleId) {
		return this.service.findRoleWithDescriptionById(roleId);
	}

	@PostMapping("createRole/{teamId}")
	public Role addNewRole(@PathVariable String teamId, @RequestBody CreateRole newRole) {

		TeamSalonRights defaultRights = new TeamSalonRights(true, true, true, true, true, true, true);

		Role role = this.service.save(
				new Role(newRole.getName(), newRole.getColor(), defaultRights, true, true, true, true, true, true));
		this.teamService.addRoleToTeam(teamId, role);

		return role;
	}

	@PatchMapping("update")
	public Role updateRole(@RequestBody RoleWithDescription role) {
		return this.service.updateFromRoleWithDesc(role);
	}

	@DeleteMapping("delete/{teamId}/{roleId}")
	public void deleteRole(@PathVariable String teamId, @PathVariable String roleId) {
		Role role = this.service.get(roleId);

		List<Salon> salons = this.salonService.findAllSalonOfTeam(teamId);
		// delete his permission of all salon
		for (Salon salon : salons) {
			this.permissionService.deleteByRoleId(role.getId());
		}
		// delete the role from the team
		this.teamService.deleteRole(teamId, roleId);
		// finally, delete the role
		this.service.delete(role.getId());
	}
}
