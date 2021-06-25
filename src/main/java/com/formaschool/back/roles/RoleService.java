package com.formaschool.back.roles;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.roles.dto.RoleCreate;
import com.formaschool.back.roles.dto.RoleWithDescription;
import com.formaschool.back.roles.dto.RoleWithoutRights;

public interface RoleService extends CRUDService<Role> {

	public List<RoleWithoutRights> findAllWithoutRights();

	public RoleWithDescription findRoleWithDescriptionById(String id);

	public List<RoleWithoutRights> findAllWithoutRightsByTeamId(String teamId);

	public Role updateFromRoleWithDesc(RoleWithDescription role);

	public Role addNewRole(String teamId, RoleCreate newRole);

	public void deleteRole(String teamId, String roleId);

	public List<RoleWithoutRights> findRoleWithoutRightsInTeamWithoutPermission(String salonId);

	public List<Role> findAllById(List<String> rolesId);

}
