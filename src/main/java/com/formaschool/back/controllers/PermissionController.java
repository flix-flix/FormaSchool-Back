package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
