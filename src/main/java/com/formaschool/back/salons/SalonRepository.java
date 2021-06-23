package com.formaschool.back.salons;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalonRepository extends MongoRepository<Salon, String> {
	public List<Salon> findByTeamId(String teamId);
}
