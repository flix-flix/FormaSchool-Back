package com.formaschool.back.services;

import java.util.List;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestBody;

import com.formaschool.back.dto.roles.CreateRole;
=======
import com.formaschool.back.dto.roles.RoleCreate;
>>>>>>> 9eeb55046fe4358dbac50348bb5108c2fe7a1823
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
	
}
