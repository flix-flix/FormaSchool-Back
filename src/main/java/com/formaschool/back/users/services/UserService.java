package com.formaschool.back.users.services;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.users.User;
import com.formaschool.back.users.dto.UserConnect;
import com.formaschool.back.users.dto.UserCreate;
import com.formaschool.back.users.dto.UserCreateWithFile;
import com.formaschool.back.users.dto.UserLocalStorage;
import com.formaschool.back.users.dto.UserNamePict;

public interface UserService extends CRUDService<User> {

	public UserNamePict getUserNamePictById(String id);

	public User addUser(UserCreate user, String idAddedBy);

	public List<UserNamePict> getUserNotInTheTeam(String teamId);

	public List<User> getUserByTeamId(String teamId);

	public User saveWithFile(UserCreateWithFile user, String idAddedBy);

	// ====================================================================================================

	public UserLocalStorage connect(UserConnect connect);
}
