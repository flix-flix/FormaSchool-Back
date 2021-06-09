package com.formaschool.back.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
	public List<Message> findBySalonId(String salonId);
}
