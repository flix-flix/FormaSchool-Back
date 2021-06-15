package com.formaschool.back.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.formaschool.back.models.Member;

public interface MemberRepository extends MongoRepository<Member, String> {
	public List<Member> findByUserId(String userId);
	public List<Member> findMemberByTeamId (String teamId);
}
