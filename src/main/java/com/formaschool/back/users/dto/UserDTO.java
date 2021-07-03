package com.formaschool.back.users.dto;

import java.time.LocalDate;

import com.formaschool.back.files.FileModel;

import lombok.Data;

@Data
public class UserDTO {
	private String id;

	private String firstname;
	private String lastname;
	private LocalDate birth;

	private String email;
	private String phone;
	private FileModel pictureFile;
	private LocalDate creation;
}
