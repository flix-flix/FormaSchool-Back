package com.formaschool.back.users.impl;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.files.FileService;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.users.User;
import com.formaschool.back.users.UserRepository;
import com.formaschool.back.users.dto.settings.UserSettings;
import com.formaschool.back.users.services.UserSettingsService;

public class UserSettingsServiceImpl implements UserSettingsService {

	private UserRepository repo;
	private FileService fileService;

	private final Logger LOGGER;
	private final Utils utils;

	public UserSettingsServiceImpl(UserRepository repo, Utils utils, FileService file) {
		this.repo = repo;
		this.utils = utils;
		LOGGER = utils.getLogger("UserService");
		this.fileService = file;

		LOGGER.equals(null);
		fileService.equals(null);
	}

	// ====================================================================================================

	@Override
	public UserSettings getSettingsById(String id) {
		return utils.dto(utils.opt(repo.findById(id)), UserSettings.class);
	}

	@Override
	public UserSettings updateSettings(UserSettings dto) {
		User user = utils.opt(repo.findById(dto.getId()));

		if (dto.getFirstname() != null)
			user.setFirstname(dto.getFirstname());
		if (dto.getLastname() != null)
			user.setLastname(dto.getLastname());
		if (dto.getEmail() != null)
			user.setEmail(dto.getEmail());
		// TODO Birth, phone
		if (dto.getBirth() != null)
			user.setBirth(dto.getBirth());
		if (dto.getPhone() != null)
			user.setPhone(dto.getPhone());
		
		User result = this.repo.save(user);
		return utils.dto(result, UserSettings.class);
	}
}
