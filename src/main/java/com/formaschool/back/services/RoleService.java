package com.formaschool.back.services;

import java.util.List;


import com.formaschool.back.dto.roles.RoleCreate;
import com.formaschool.back.dto.roles.RoleWithDescription;
import com.formaschool.back.dto.roles.RoleWithoutRights;
import com.formaschool.back.models.Member;
import com.formaschool.back.models.Role;

public interface RoleService extends CRUDService<Role> {

	public List<RoleWithoutRights> findAllWithoutRights();

	public RoleWithDescription findRoleWithDescriptionById(String id);

	public List<RoleWithoutRights> findAllWithoutRightsByTeamId(String teamId);

	public Role updateFromRoleWithDesc(RoleWithDescription role);

	public Role addNewRole(String teamId, RoleCreate newRole);

	public void deleteRole(String teamId, String roleId);
	
	public List<Role> findRoleMissingByMember(Member member);
	
	public List<RoleWithoutRights> findRoleWithoutRightsInTeamWithoutPermission(String salonId);
	
}
