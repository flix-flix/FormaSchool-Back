package com.formaschool.back.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Member;

public interface MemberRepository extends MongoRepository<Member, String> {
}
