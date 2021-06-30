package com.formaschool.back.members.dto;

import java.util.List;

import com.formaschool.back.roles.Role;
import com.formaschool.back.roles.dto.RoleWithoutRights;
import com.formaschool.back.users.dto.UserNamePict;

import lombok.Data;

@Data
public class MemberRoles {

	private String id;
	private UserNamePict user;
	private List<RoleWithoutRights> roles;
}
