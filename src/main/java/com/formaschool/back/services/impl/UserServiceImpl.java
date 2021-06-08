package com.formaschool.back.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.formaschool.back.dto.user.UserName;
import com.formaschool.back.dto.user.UserNamePict;
import com.formaschool.back.models.User;
import com.formaschool.back.repositories.UserRepository;
import com.formaschool.back.services.UserService;

public class UserServiceImpl extends CRUDServiceImpl<User> implements UserService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserName getUserNameById(String id) {
		User entity = get(repo.findById(id));
		return mapper.convertValue(entity, UserName.class);
	}

	@Override
	public UserNamePict getUserNamePictById(String id) {
		User entity = get(repo.findById(id));
		return mapper.convertValue(entity, UserNamePict.class);
	}
}
