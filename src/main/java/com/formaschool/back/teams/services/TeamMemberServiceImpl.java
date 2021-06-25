package com.formaschool.back.teams.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.teams.Team;
import com.formaschool.back.teams.dto.TeamNamePict;

public class TeamMemberServiceImpl implements TeamMemberService {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ObjectMapper mapper;

	public TeamMemberServiceImpl(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public List<TeamNamePict> findAllTeamOfUser(String userId) {
		return memberService.findAllByUserId(userId).stream().map(member -> dto(member.getTeam(), TeamNamePict.class))
				.collect(Collectors.toList());
	}

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
