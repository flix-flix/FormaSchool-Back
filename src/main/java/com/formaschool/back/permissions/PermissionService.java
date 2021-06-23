package com.formaschool.back.permissions;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.permissions.dto.PermissionMemberUserRoleWithoutRights;
import com.formaschool.back.permissions.dto.PermissionRights;

public interface PermissionService extends CRUDService<Permission> {

	public void deleteByRoleId(String roleId);
	
	public List<PermissionMemberUserRoleWithoutRights> findPermissionBySalonId(String salonId);
	
	public PermissionRights findPermissionRightsById(String permissionId);
	
	public PermissionRights updatePermission(PermissionRights permissionRights);
	
	public Permission findBySalonIdAndMemberId(String salonId, String memberId);
	
	public Permission findBySalonIdAndRoleId(String salonId, String roleId);
	
	public PermissionMemberUserRoleWithoutRights  addFromRole(String salonId, String roleId);
	
	public PermissionMemberUserRoleWithoutRights  addFromMember(String salonId, String memberId);
}
