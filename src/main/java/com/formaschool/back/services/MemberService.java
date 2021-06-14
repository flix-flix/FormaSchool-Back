package com.formaschool.back.services;

import java.util.List;


import com.formaschool.back.models.Member;

public interface MemberService extends CRUDService<Member> {
	public List<Member> findAllByUserId(String userId);
	
	public List<Member> findMemberByTeamId(String teamId);
}
