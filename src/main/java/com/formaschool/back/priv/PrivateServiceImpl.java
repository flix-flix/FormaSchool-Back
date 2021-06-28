package com.formaschool.back.priv;

import java.util.List;
import java.util.stream.Collectors;

import com.formaschool.back.members.MemberService;
import com.formaschool.back.priv.dto.Private;
import com.formaschool.back.salons.SalonService;
import com.formaschool.back.teams.services.TeamService;

public class PrivateServiceImpl implements PrivateService {

	private MemberService memberService;
	private TeamService teamService;
	private SalonService salonService;

	public PrivateServiceImpl(MemberService member, TeamService team, SalonService salon) {
		this.memberService = member;
		this.teamService = team;
		this.salonService = salon;
	}

	@Override
	public List<Private> findAllPrivateOfUser(String userId) {
		System.out.println(memberService.findAllByUserId(userId));

		return memberService.findAllPrivateByUserId(userId).stream()
				.map(member -> teamService.findById(member.getTeam().getId()))
				.map(team -> new Private(memberService.findMemberUserPseudoByTeamId(team.getId()),
						salonService
								.getSalonWithMessages(salonService.findAllSalonOfTeam(team.getId()).get(0).getId())))
				.collect(Collectors.toList());
	}
}
