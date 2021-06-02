package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.User;

public interface UserRepository extends MongoRepository<User, String> {
}
