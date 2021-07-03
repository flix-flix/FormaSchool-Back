package com.formaschool.back.members.dto;

import java.util.List;

import com.formaschool.back.roles.Role;
import com.formaschool.back.teams.Team;
import com.formaschool.back.users.dto.UserDTO;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String pseudo;
	private UserDTO user;
	private Team team;
	private List<Role> roles;
	private boolean priv;
}
