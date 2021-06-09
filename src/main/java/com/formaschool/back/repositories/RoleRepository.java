package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

}
