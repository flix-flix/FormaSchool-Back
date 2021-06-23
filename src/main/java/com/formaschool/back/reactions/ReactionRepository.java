package com.formaschool.back.reactions;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReactionRepository extends MongoRepository<Reaction, String> {

	public List<Reaction> findAllByMessageId(String msgId);
}
