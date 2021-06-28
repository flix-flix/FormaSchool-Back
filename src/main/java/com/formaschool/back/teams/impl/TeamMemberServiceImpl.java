package com.formaschool.back.teams.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.teams.dto.TeamNamePict;
import com.formaschool.back.teams.services.TeamMemberService;

public class TeamMemberServiceImpl implements TeamMemberService {

	private Utils utils;
	private MemberService memberService;

	public TeamMemberServiceImpl(Utils utils, MemberService memberService) {
		this.utils = utils;
		this.memberService = memberService;
	}

	@Override
	public List<TeamNamePict> findAllTeamOfUser(String userId) {
		return memberService.findAllByUserId(userId).stream().filter(member -> !member.isPriv())
				.map(member -> utils.dto(member.getTeam(), TeamNamePict.class)).collect(Collectors.toList());
	}
}
