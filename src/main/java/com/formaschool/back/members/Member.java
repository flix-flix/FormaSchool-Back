package com.formaschool.back.members;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formaschool.back.roles.Role;
import com.formaschool.back.teams.Team;
import com.formaschool.back.users.User;

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

	private boolean priv;

	public Member(String pseudo, User user, Team team, List<Role> roles, boolean priv) {
		this.pseudo = pseudo;
		this.user = user;
		this.team = team;
		this.roles = roles;
		this.priv = priv;
	}

	public Member(String pseudo, User user, Team team, List<Role> roles) {
		this(pseudo, user, team, roles, false);
	}

	public Member(String id) {
		this.id = id;
	}
}
