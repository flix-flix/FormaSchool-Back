package com.formaschool.back.models;

import java.util.List;

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
public class Member {

	@Id
	private String id;
	
	private String pseudo;

	@DBRef
	private User user;
	@DBRef
	private Team team;
	@DBRef
	private List<Role> roles;
}
