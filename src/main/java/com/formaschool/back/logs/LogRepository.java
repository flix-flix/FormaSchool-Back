package com.formaschool.back.logs;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
	
	public List<Log> findByTeamId(String teamId);
	
	public List<Log> findByTeamIdNull();

}
