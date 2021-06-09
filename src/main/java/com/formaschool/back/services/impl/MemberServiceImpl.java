package com.formaschool.back.services.impl;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.Member;
import com.formaschool.back.repositories.MemberRepository;
import com.formaschool.back.services.MemberService;

public class MemberServiceImpl extends CRUDServiceImpl<Member> implements MemberService {

	private MemberRepository repo;

	public MemberServiceImpl(MemberRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}

	@Override
	public List<Member> findAllByUserId(String userId) {
		return repo.findByUserId(userId);
	}
}
