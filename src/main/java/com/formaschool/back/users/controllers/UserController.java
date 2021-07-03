package com.formaschool.back.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.users.User;
import com.formaschool.back.users.dto.UserConnect;
import com.formaschool.back.users.dto.UserCreate;
import com.formaschool.back.users.dto.UserCreateWithFile;
import com.formaschool.back.users.dto.UserLocalStorage;
import com.formaschool.back.users.dto.UserNamePict;
import com.formaschool.back.users.services.UserService;

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

	@GetMapping("userNotInTheTeam/{teamId}")
	public List<UserNamePict> getUserNotInTheTeam(@PathVariable String teamId) {
		return this.service.getUserNotInTheTeam(teamId);
	}

	@GetMapping("namePict/{id}")
	public UserNamePict getNamePict(@PathVariable String id) {
		return service.getUserNamePictById(id);
	}

	@PostMapping("connect")
	public UserLocalStorage connect(@RequestBody UserConnect connect) {
		return service.connect(connect);
	}

	@PostMapping("add")
	public User addUser(@RequestHeader("Authorization") String authorization, @RequestBody UserCreate user) {
		String userId = authorization.split(" ")[1];
		return this.service.addUser(user, userId);
	}

	@PostMapping("saveWithFile")
	public User saveWithFile(@RequestHeader("Authorization") String authorization,
			@RequestBody UserCreateWithFile user) {
		String userId = authorization.split(" ")[1];
		return this.service.saveWithFile(user, userId);
	}
}
