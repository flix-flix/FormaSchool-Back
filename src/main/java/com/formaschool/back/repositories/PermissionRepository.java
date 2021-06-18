package com.formaschool.back.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Permission;

public interface PermissionRepository extends MongoRepository<Permission, String>{
	
	public List<Permission> findByRoleId(String roleId);
	
	public List<Permission> findBySalonId(String salonId);
	
	public Permission findBySalonIdAndMemberId(String salonId, String memberId);

}
