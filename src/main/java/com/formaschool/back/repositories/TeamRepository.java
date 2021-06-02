package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Team;

public interface TeamRepository extends MongoRepository<Team, String> {
}
