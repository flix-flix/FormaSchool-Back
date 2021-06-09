package com.formaschool.back.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Team {

	@Id
	private String id;

	@NonNull
	private String name;
	private String desc;
	private String picture;

	@DBRef
	private List<Role> roles;

	public Team(@NonNull String name, String desc, String picture, List<Role> roles) {
		this.name = name;
		this.desc = desc;
		this.picture = picture;
		this.roles = roles;
	}
}
