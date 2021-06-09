package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Permission;

public interface PermissionRepository extends MongoRepository<Permission, String>{

}
