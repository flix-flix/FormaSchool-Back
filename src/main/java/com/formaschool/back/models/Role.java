package com.formaschool.back.models;

import java.time.LocalDate;

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
public class Role {

	@Id
	private String id;
	
	private String nom;
	private String color;
	@DBRef
	private TeamSalonRights commonRights;
	private Boolean manageSalon;
	private Boolean manageEmoji;
	private Boolean teamManage;
	private Boolean seeLogs;
	//Change your pseudo
	private Boolean changePseudo;
	//Change every Pseudo
	private Boolean managePseudo;
}
