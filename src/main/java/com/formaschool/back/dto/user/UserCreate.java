package com.formaschool.back.dto.user;

import lombok.Data;

@Data
public class UserCreate {

	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private String picture;
}
