package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Reaction;

public interface ReactionRepository extends MongoRepository<Reaction, String> {
}
