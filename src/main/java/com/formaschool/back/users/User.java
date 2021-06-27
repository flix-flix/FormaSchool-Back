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
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	//
//	private int age;
//	private int phone;
	//
	
	@DBRef
	private FileModel pictureFile;

	private LocalDate creation;


	public User(String firstname, String lastname, String password, String email, FileModel pictureFile,
			//
//			int age, int phone,
			//
			LocalDate creation) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		//
//		this.age = age;
//		this.phone = phone;
		//
		this.pictureFile = pictureFile;
		this.creation = creation;
	}
	public User(String id) {
		super();
		this.id = id;
	}
	

	@Transient
	public String getPicture() {
		return pictureFile!=null? pictureFile.getId():null;
	}


}
