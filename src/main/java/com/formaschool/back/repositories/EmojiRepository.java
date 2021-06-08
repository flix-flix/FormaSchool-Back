package com.formaschool.back.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Emoji;

public interface EmojiRepository extends MongoRepository<Emoji, String> {
	
	public List<Emoji> findByUserNotNullAndTeamId(String teamId);
	
	public List<Emoji> findByUserNotNullAndTeamNull();

}
