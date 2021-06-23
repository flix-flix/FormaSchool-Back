package com.formaschool.back.users.dto;

import lombok.Data;

@Data
public class UserCreate {

	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private String picture;
}
