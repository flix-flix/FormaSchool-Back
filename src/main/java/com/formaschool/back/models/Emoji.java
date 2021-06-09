package com.formaschool.back.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Emoji {

	@Id
	private String id;
	
	@DBRef
	private User user;
	@DBRef
	private Team team;

	private String name;
	private String picture;

	public Emoji(User user, Team team, String name, String picture) {
		this.user = user;
		this.team = team;
		this.name = name;
		this.picture = picture;
	}
}
