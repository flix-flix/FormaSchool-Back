package com.formaschool.back.users.services;

import com.formaschool.back.users.dto.settings.UserSettings;

public interface UserSettingsService {

	public UserSettings getSettingsById(String id);

	public UserSettings updateSettings(UserSettings dto);
}
