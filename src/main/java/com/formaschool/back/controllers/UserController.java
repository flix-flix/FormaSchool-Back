package com.formaschool.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.user.UserCreate;
import com.formaschool.back.dto.user.UserName;
import com.formaschool.back.dto.user.UserNamePict;
import com.formaschool.back.dto.user.UserSettings;
import com.formaschool.back.models.User;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController implements CRUDController<User> {
	@Autowired
	private UserService service;

	@Override
	public CRUDService<User> getGenericService() {
		return service;
	}

	@GetMapping("name/{id}")
	public UserName getName(@PathVariable String id) {
		return service.getUserNameById(id);
	}

	@GetMapping("namePict/{id}")
	public UserNamePict getNamePict(@PathVariable String id) {
		return service.getUserNamePictById(id);
	}

	// TODO [Remove]
	@GetMapping("default")
	public UserNamePict getDefaultUser() {
		return service.getDefaultUser();
	}

//	ajout get pour userSettings
	@GetMapping("userSettings/{id}")
	public UserSettings getuserSettings(@PathVariable String id) {
		return service.getUserSettingsById(id);
	}
	
	@PostMapping("add")
	public User addUser(@RequestBody UserCreate user) {
		return this.service.addUser(user);
	}
}
