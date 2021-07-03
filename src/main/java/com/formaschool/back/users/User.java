package com.formaschool.back.users;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formaschool.back.files.FileModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

	@Id
	private String id;

	private String email;
	private String password;

	private String firstname;
	private String lastname;
	private LocalDate birth;

	private String phone;

	@DBRef
	private FileModel pictureFile;

	private LocalDate creation;

	public User(String firstname, String lastname, String password, String email, FileModel pictureFile, LocalDate birth,
			String phone, LocalDate creation) {
		this.firstname = firstname;
		this.lastname = lastname;

		this.email = email;
		this.password = password;

		this.birth = birth;
		this.phone = phone;

		this.pictureFile = pictureFile;
		this.creation = creation;
	}

	public User(String id) {
		this.id = id;
	}

	@Transient
	public String getPicture() {
		return pictureFile != null ? pictureFile.getId() : null;
	}
}
