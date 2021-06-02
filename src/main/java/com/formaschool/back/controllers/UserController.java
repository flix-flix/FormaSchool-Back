package com.formaschool.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.models.User;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.UserService;

@RestController
@RequestMapping("users")
public class UserController implements CRUDController<User> {
	@Autowired
	private UserService service;

	@Override
	public CRUDService<User> getGenericService() {
		return service;
	}
}
