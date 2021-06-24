package com.formaschool.back.roles;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

	public List<Role> findAllById(List<String> idRoles);

}
