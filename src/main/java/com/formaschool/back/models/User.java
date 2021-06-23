package com.formaschool.back.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	
	@DBRef
	private FileModel pictureFile;

	private LocalDate creation;

	public User(String firstname, String lastname, String password, String email, FileModel pictureFile,
			LocalDate creation) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.pictureFile = pictureFile;
		this.creation = creation;
	}

	@Transient
	public String getPicture() {
		return pictureFile!=null? pictureFile.getId():null;
	}
	
}
