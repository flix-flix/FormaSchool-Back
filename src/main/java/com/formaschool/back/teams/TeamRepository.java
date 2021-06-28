package com.formaschool.back.teams;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
	public List<Team> findAllById(List<String> teamIds);
}
