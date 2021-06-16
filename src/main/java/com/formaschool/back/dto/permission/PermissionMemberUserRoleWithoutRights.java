package com.formaschool.back.dto.permission;

import com.formaschool.back.dto.member.MemberUserPseudo;
import com.formaschool.back.dto.roles.RoleWithoutRights;

import lombok.Data;

@Data
public class PermissionMemberUserRoleWithoutRights {
	
	private String id;
	
	private MemberUserPseudo member;
	
	private RoleWithoutRights role;

}
