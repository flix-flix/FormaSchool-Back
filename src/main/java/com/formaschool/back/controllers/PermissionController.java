package com.formaschool.back.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.permission.PermissionMemberUserRoleWithoutRights;
import com.formaschool.back.dto.permission.PermissionRights;
import com.formaschool.back.models.Permission;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.PermissionService;

@CrossOrigin
@RestController
@RequestMapping("permissions")
public class PermissionController implements CRUDController<Permission> {

	@Autowired
	private PermissionService service;

	@Override
	public CRUDService<Permission> getGenericService() {
		return service;
	}
	
	@GetMapping("bySalon/{salonId}")
	public List<PermissionMemberUserRoleWithoutRights> findPermissionBySalonId(@PathVariable String salonId){
		return this.service.findPermissionBySalonId(salonId);
	}
	
	@GetMapping("permissionRights/{permissionId}")
	public PermissionRights findPermissionRightsById(@PathVariable String permissionId) {
		return this.service.findPermissionRightsById(permissionId);
	}
	
	@PatchMapping("update")
	public PermissionRights updatePermission(@RequestBody PermissionRights permissionRights) {
		return this.service.updatePermission(permissionRights);
	}
	
	@PostMapping("addFromRole/{salonId}/{roleId}")
	public PermissionMemberUserRoleWithoutRights  addFromRole(@PathVariable String salonId, @PathVariable String roleId) {
		return this.service.addFromRole(salonId, roleId);
	}
	
	@PostMapping("addFromMember/{salonId}/{memberId}")
	public PermissionMemberUserRoleWithoutRights  addFromMember(@PathVariable String salonId, @PathVariable String memberId) {
		return this.service.addFromMember(salonId, memberId);
	}
}
