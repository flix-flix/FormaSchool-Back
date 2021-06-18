package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.member.MemberUserPseudo;
import com.formaschool.back.models.Member;
import com.formaschool.back.repositories.MemberRepository;
import com.formaschool.back.services.MemberService;
import com.formaschool.back.services.PermissionService;

public class MemberServiceImpl extends CRUDServiceImpl<Member> implements MemberService {

	private MemberRepository repo;
	private PermissionService permissionService;


	public MemberServiceImpl(MemberRepository repo, PermissionService permissionService, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
		this.permissionService = permissionService;
	}

	@Override
	public List<Member> findAllByUserId(String userId) {
		return repo.findByUserId(userId);
	}

	@Override
	public List<Member> findMembersByTeamId(String teamId) {
		return this.repo.findMemberByTeamId(teamId);
	}

	@Override
	public List<MemberUserPseudo> findMembersInTeamWithoutPermissionForSalon(String teamId, String salonId) {
		List<Member> members = this.repo.findMemberByTeamId(teamId);
		return members.stream()
		.filter(member -> this.permissionService.findBySalonIdAndMemberId(salonId, member.getId())!=null)
		.map(member -> dto(member, MemberUserPseudo.class))
		.collect(Collectors.toList());
	}
}
