package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Salon;

public interface SalonRepository extends MongoRepository<Salon, String> {
}
