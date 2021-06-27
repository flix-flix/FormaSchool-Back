package com.formaschool.back.teams.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.teams.Team;
import com.formaschool.back.teams.dto.TeamNamePict;
import com.formaschool.back.teams.services.TeamMemberService;

public class TeamMemberServiceImpl implements TeamMemberService {

	private MemberService memberService;
	private ObjectMapper mapper;

	public TeamMemberServiceImpl(ObjectMapper mapper, MemberService memberService) {
		this.mapper = mapper;
		this.memberService = memberService;
	}

	@Override
	public List<TeamNamePict> findAllTeamOfUser(String userId) {
		return memberService.findAllByUserId(userId).stream().filter(member -> !member.isPriv())
				.map(member -> dto(member.getTeam(), TeamNamePict.class)).collect(Collectors.toList());
	}

	// ===================================================================

	/** Throw BAD_REQUEST if the Optional is empty */
	protected Team opt(Optional<Team> opt) {
		return opt.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
	}

	/** Map the entity into the DTO */
	protected <E, DTO> DTO dto(E entity, Class<DTO> cl) {
		return mapper.convertValue(entity, cl);
	}

	/** Map the entity into the DTO */
	protected <DTO> DTO dtoOpt(Optional<Team> opt, Class<DTO> cl) {
		return dto(opt(opt), cl);
	}

}
