package com.formaschool.back.roles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formaschool.back.rights.TeamSalonRights;

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

	private String name;
	private String color;

	private TeamSalonRights commonRights;

	/** Change team's name and desc */
	private Boolean manageTeam;

	private Boolean createDeleteSalon;

	/** Create, delete , update emoji */
	private Boolean manageEmoji;

	/** See logs */
	private Boolean seeLogs;

	/** Change your pseudo */
	private Boolean changePseudo;
	/** Change every pseudo */
	private Boolean managePseudo;

	public Role(String name, String color, TeamSalonRights commonRights, Boolean manageTeam, Boolean createDeleteSalon,
			Boolean manageEmoji, Boolean seeLogs, Boolean changePseudo, Boolean managePseudo) {
		this.name = name;
		this.color = color;
		this.commonRights = commonRights;
		this.manageTeam = manageTeam;
		this.createDeleteSalon = createDeleteSalon;
		this.manageEmoji = manageEmoji;
		this.seeLogs = seeLogs;
		this.changePseudo = changePseudo;
		this.managePseudo = managePseudo;
	}

	public Role(String id) {
		this.id = id;
	}
}
