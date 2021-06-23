package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.user.UserConnect;
import com.formaschool.back.dto.user.UserCreate;
import com.formaschool.back.dto.user.UserCreateWithFile;
import com.formaschool.back.dto.user.UserLocalStorage;
import com.formaschool.back.dto.user.UserName;
import com.formaschool.back.dto.user.UserNamePict;
import com.formaschool.back.dto.user.UserSettings;
import com.formaschool.back.models.User;

public interface UserService extends CRUDService<User> {

	public UserName getUserNameById(String id);

	public UserNamePict getUserNamePictById(String id);

	public User addUser(UserCreate user);

	public List<UserNamePict> getUserNotInTheTeam(String teamId);

	public List<User> getUserByTeamId(String teamId);

	public User saveWithFile(UserCreateWithFile user);

	// ====================================================================================================

	// TODO [Remove]
	public UserNamePict getDefaultUser();

	public UserLocalStorage connect(UserConnect connect);

	// ====================================================================================================
	// userSettings

	public UserSettings getUserSettingsById(String id);

	public UserSettings updateuserSettings(UserSettings dto);
}
