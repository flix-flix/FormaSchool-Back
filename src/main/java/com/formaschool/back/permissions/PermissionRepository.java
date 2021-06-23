package com.formaschool.back.permissions;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String>{
	
	public List<Permission> findByRoleId(String roleId);
	
	public List<Permission> findBySalonId(String salonId);
	
	public Permission findBySalonIdAndMemberId(String salonId, String memberId);
	
	public Permission findBySalonIdAndRoleId(String salonId, String roleId);

}
