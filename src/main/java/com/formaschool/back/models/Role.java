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
public class Role {

	@Id
	private String id;
	
	private String nom;
	private String color;
	@DBRef
	private TeamSalonRights commonRights;
	
	/** Change team's name and desc*/
	private Boolean manageTeam;
		
	private Boolean createDeleteSalon;
	
	/** Create, delete , update emoji*/
	private Boolean manageEmoji;
	
	/** See logs */
	private Boolean seeLogs;
	/** Change your pseudo */
	private Boolean changePseudo;
	/** Change every pseudo */
	private Boolean managePseudo;
}
