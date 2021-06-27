package com.formaschool.back.salons;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formaschool.back.teams.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Salon {

	@Id
	private String id;
	private String name;
	private String desc;

	@DBRef
	private Team team;

	public Salon(String name, String desc, Team team) {
		this.name = name;
		this.desc = desc;
		this.team = team;
	}

	public Salon(String id) {
		this.id = id;
	}
}
