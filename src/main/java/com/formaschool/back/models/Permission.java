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
public class Permission {

	@Id
	private String id;
	
	@DBRef
	private Salon salon;
	
	/**A Permission is linked to a member XOR a role */
	@DBRef
	private Member member;
	
	@DBRef
	private Role role;

	@DBRef
	private TeamSalonRights commonRights;

	public Permission(Salon salon, Member member, Role role, TeamSalonRights commonRights) {
		super();
		this.salon = salon;
		this.member = member;
		this.role = role;
		this.commonRights = commonRights;
	}
}
