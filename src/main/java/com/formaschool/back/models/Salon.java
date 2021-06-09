package com.formaschool.back.models;

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
public class Salon {

	@Id
	private String id;
	@NonNull
	private String name;
	private String desc;

	@DBRef
	private Team team;

	public Salon(@NonNull String name, String desc, Team team) {
		this.name = name;
		this.desc = desc;
		this.team = team;
	}
}
