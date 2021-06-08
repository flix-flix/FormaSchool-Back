package com.formaschool.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.user.UserName;
import com.formaschool.back.dto.user.UserNamePict;
import com.formaschool.back.dto.user.UserSettings;
import com.formaschool.back.models.User;
import com.formaschool.back.repositories.UserRepository;
import com.formaschool.back.services.UserService;

public class UserServiceImpl extends CRUDServiceImpl<User> implements UserService {

	private UserRepository repo;

	public UserServiceImpl(UserRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}

	@Override
	public UserName getUserNameById(String id) {
		return map(repo.findById(id), UserName.class);
	}

	@Override
	public UserNamePict getUserNamePictById(String id) {
		return map(repo.findById(id), UserNamePict.class);
	}
	
	// ajout pour userSettings
	@Override
	public UserSettings getUserSettingsById(String id) {
		return map(repo.findById(id), UserSettings.class);
	}
}
