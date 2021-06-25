package com.formaschool.back.teams;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formaschool.back.files.FileModel;
import com.formaschool.back.roles.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Team {

	@Id
	private String id;

	private String name;
	private String desc;

	@DBRef
	private FileModel pictureFile;

	@DBRef
	private List<Role> roles;

	public Team(String name, String desc, FileModel pictureFile, List<Role> roles) {
		this.name = name;
		this.desc = desc;
		this.pictureFile = pictureFile;
		this.roles = roles;
	}
	public Team(String id) {
		super();
		this.id = id;
	}
	

	@Transient
	public String getPicture() {
		return pictureFile != null ? pictureFile.getId() : null;
	}
}
