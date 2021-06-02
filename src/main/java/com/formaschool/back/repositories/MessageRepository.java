package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
}
