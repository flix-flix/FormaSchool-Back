package com.formaschool.back.members;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {
	public List<Member> findByUserId(String userId);

	public List<Member> findMemberByTeamId(String teamId);

	public List<Member> findByUserIdAndPrivTrue(String userId);
}
