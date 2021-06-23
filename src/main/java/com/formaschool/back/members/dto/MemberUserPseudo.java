package com.formaschool.back.members.dto;

import com.formaschool.back.users.dto.UserNamePict;

import lombok.Data;

@Data
public class MemberUserPseudo {
	private UserNamePict user;
	private String pseudo;
}
