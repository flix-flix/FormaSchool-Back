package com.formaschool.back.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._init.InitController;
import com.formaschool.back.dto.user.UserName;
import com.formaschool.back.dto.user.UserNamePict;
import com.formaschool.back.dto.user.UserSettings;
import com.formaschool.back.models.User;
import com.formaschool.back.repositories.UserRepository;
import com.formaschool.back.services.UserService;

public class UserServiceImpl extends CRUDServiceImpl<User> implements UserService {

	@Autowired
	private InitController init;
	private boolean isInit = false;

	private UserRepository repo;

	public UserServiceImpl(UserRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}

	@Override
	public UserName getUserNameById(String id) {
		return dtoOpt(repo.findById(id), UserName.class);
	}

	@Override
	public UserNamePict getUserNamePictById(String id) {
		return dtoOpt(repo.findById(id), UserNamePict.class);
	}

	// ajout pour userSettings
	@Override
	public UserSettings getUserSettingsById(String id) {
		return dtoOpt(repo.findById(id), UserSettings.class);
	}

	@Override
	public UserNamePict getDefaultUser() {
		try {
			if (!isInit) {
				init.drop();
				init.init();
				isInit = true;
			}
		} catch (ResponseStatusException e) {
		}
		return dtoOpt(repo.findAll().stream().filter(user -> user.getFirstname().equals("Félix")).findFirst(),
				UserNamePict.class);
	}
}
