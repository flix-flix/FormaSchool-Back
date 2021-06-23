package com.formaschool.back.permissions.dto;

import com.formaschool.back.members.dto.MemberUserPseudo;
import com.formaschool.back.roles.dto.RoleWithoutRights;

import lombok.Data;

@Data
public class PermissionMemberUserRoleWithoutRights {
	
	private String id;
	
	private MemberUserPseudo member;
	
	private RoleWithoutRights role;

}
