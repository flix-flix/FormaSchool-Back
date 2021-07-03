package com.formaschool.back.users.dto.settings;

import lombok.Data;

@Data
public class UserPassword {
	private String id;
	private String password;
	private String passwordNew;
}
