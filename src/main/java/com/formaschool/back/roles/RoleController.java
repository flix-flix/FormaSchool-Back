package com.formaschool.back.roles;

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

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.members.Member;
import com.formaschool.back.roles.dto.RoleCreate;
import com.formaschool.back.roles.dto.RoleWithDescription;
import com.formaschool.back.roles.dto.RoleWithoutRights;

@CrossOrigin
@RestController
@RequestMapping("roles")
public class RoleController implements CRUDController<Role> {

	@Autowired
	private RoleService service;

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
		return this.service.findAllWithoutRightsByTeamId(teamId);
	}

	@GetMapping("withDesc/{roleId}")
	public RoleWithDescription findRoleWithDescriptionById(@PathVariable String roleId) {
		return this.service.findRoleWithDescriptionById(roleId);
	}

	@PostMapping("createRole/{teamId}")

	public Role addNewRole(@PathVariable String teamId, @RequestBody RoleCreate newRole) {
		return this.service.addNewRole(teamId, newRole);
	}

	@PatchMapping("update")
	public Role updateRole(@RequestBody RoleWithDescription role) {
		return this.service.updateFromRoleWithDesc(role);
	}

	@DeleteMapping("delete/{teamId}/{roleId}")
	public void deleteRole(@PathVariable String teamId, @PathVariable String roleId) {
		this.service.deleteRole(teamId, roleId);
	}
	
	@GetMapping("findRoleMissingByMember")
	public List<Role> findRoleMissingByMember(@RequestBody Member member){
		return null;
	}
	
	@GetMapping("withoutPermission/{salonId}")
	public List<RoleWithoutRights> findRoleWithoutRightsInTeamWithoutPermission(@PathVariable String salonId){
		return this.service.findRoleWithoutRightsInTeamWithoutPermission(salonId);
	}
	

}
