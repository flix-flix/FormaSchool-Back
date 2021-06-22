package com.formaschool.back.dto.user;

import lombok.Data;

@Data
public class UserCreateWithFile {
	
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private String file;
	private String filename;

}
