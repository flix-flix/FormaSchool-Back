package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.roles.RoleWithoutRights;
import com.formaschool.back.models.Role;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.RoleService;

@CrossOrigin
@RestController
@RequestMapping("roles")
public class RoleController implements CRUDController<Role>{

	@Autowired
	private RoleService service;

	@Override
	public CRUDService<Role> getGenericService() {
		return service;
	}
	
	@GetMapping("withoutRights")
	public List<RoleWithoutRights> findAllWithoutRights(){
		return this.service.findAllWithoutRights();
	}

}
