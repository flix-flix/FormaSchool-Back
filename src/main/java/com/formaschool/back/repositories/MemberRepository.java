package com.formaschool.back.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formaschool.back.models.Member;

public interface MemberRepository extends MongoRepository<Member, String> {
	public List<Member> findByUserId(String userId);
}
