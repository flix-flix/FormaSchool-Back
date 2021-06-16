package com.formaschool.back.dto.user;

import java.util.List;

import com.formaschool.back.models.Member;

import lombok.Data;

@Data
public class UserLocalStorage {
	private String id;

	private String firstname;
	private String lastname;
	private String picture;

	private List<Member> members;
}
