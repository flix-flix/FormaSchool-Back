package com.formaschool.back.users;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.users.dto.UserConnect;
import com.formaschool.back.users.dto.UserCreate;
import com.formaschool.back.users.dto.UserCreateWithFile;
import com.formaschool.back.users.dto.UserLocalStorage;
import com.formaschool.back.users.dto.UserName;
import com.formaschool.back.users.dto.UserNamePict;
import com.formaschool.back.users.dto.UserSettings;

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
