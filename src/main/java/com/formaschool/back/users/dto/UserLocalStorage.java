package com.formaschool.back.users.dto;

import java.util.List;

import com.formaschool.back.members.dto.MemberDTO;

import lombok.Data;

@Data
public class UserLocalStorage {
	private String id;

	private String firstname;
	private String lastname;
	private String picture;

	private List<MemberDTO> members;
}
