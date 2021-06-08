package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Log;

public interface LogRepository extends MongoRepository<Log, String> {

}
