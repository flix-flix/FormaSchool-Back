package com.formaschool.back.services;

import com.formaschool.back.dto.user.UserName;
import com.formaschool.back.dto.user.UserNamePict;
import com.formaschool.back.models.User;

public interface UserService extends CRUDService<User> {

	public UserName getUserNameById(String id);

	public UserNamePict getUserNamePictById(String id);
}
