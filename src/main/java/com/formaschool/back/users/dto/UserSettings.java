package com.formaschool.back.users.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserSettings {
	private String id;
	private String firstname;
	private String lastname;
	private String email;
//	private int age;
//	private int phone;
	private String password;
	private LocalDate creation;
	private String picture;
}
