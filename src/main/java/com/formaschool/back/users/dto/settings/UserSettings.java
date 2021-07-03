package com.formaschool.back.users.dto.settings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserSettings {

	private String id;

	private String firstname;
	private String lastname;
	private LocalDate birth;

	private String email;
	private String phone;

	private String picture;
}
