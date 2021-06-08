package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Emoji;

public interface EmojiRepository extends MongoRepository<Emoji, String> {

}
