package com.formaschool.back.emojis;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmojiRepository extends MongoRepository<Emoji, String> {
	
	public List<Emoji> findByUserNotNullAndTeamId(String teamId);
	
	public List<Emoji> findByUserNotNullAndTeamNull();
	
	public List<Emoji> findByUserNullAndTeamNull();
	
	public List<Emoji> findByIdNotAndName(String Id, String name);

}
