package com.formaschool.back.users;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	public Optional<User> findByEmail(String email);
}
