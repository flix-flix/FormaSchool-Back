package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.File;

public interface FileRepository extends MongoRepository<File, String> {
}
