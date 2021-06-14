package com.formaschool.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
