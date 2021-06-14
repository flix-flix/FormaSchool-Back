package com.formaschool.back.dto.user;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserSettings {
	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private LocalDate creation;
	private String picture;
}
