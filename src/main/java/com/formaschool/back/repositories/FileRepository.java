package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.FileModel;

public interface FileRepository extends MongoRepository<FileModel, String> {
}
