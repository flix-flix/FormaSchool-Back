package com.formaschool.back.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	public Optional<User> findByEmail(String email);
}
