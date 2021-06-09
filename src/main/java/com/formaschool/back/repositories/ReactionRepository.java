package com.formaschool.back.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Reaction;

public interface ReactionRepository extends MongoRepository<Reaction, String> {

	public List<Reaction> findAllByMessageId(String id);
}
