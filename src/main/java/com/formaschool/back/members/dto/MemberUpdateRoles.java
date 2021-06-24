package com.formaschool.back.members.dto;

import java.util.List;

import lombok.Data;

@Data
public class MemberUpdateRoles {

	private String id;
	private List<String> rolesId;
}
