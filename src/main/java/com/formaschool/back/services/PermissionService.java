package com.formaschool.back.services;

import java.util.List;


import com.formaschool.back.dto.permission.PermissionMemberUserRoleWithoutRights;
import com.formaschool.back.dto.permission.PermissionRights;
import com.formaschool.back.models.Permission;

public interface PermissionService extends CRUDService<Permission> {

	public void deleteByRoleId(String roleId);
	
	public List<PermissionMemberUserRoleWithoutRights> findPermissionBySalonId(String salonId);
	
	public PermissionRights findPermissionRightsById(String permissionId);
	
	public PermissionRights updatePermission(PermissionRights permissionRights);
}
