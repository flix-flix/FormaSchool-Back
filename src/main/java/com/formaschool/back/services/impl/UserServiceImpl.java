package com.formaschool.back.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.user.UserCreate;
import com.formaschool.back.dto.user.UserName;
import com.formaschool.back.dto.user.UserNamePict;
import com.formaschool.back.dto.user.UserSettings;
import com.formaschool.back.models.Member;
import com.formaschool.back.models.User;
import com.formaschool.back.repositories.UserRepository;
import com.formaschool.back.services.MemberService;
import com.formaschool.back.services.UserService;

public class UserServiceImpl extends CRUDServiceImpl<User> implements UserService {

	private UserRepository repo;
	private MemberService memberService;

	public UserServiceImpl(UserRepository repo, MemberService memberService, ObjectMapper mapper) {
		super(repo, mapper);
		this.memberService = memberService;
		this.repo = repo;
	}

	@Override
	public UserName getUserNameById(String id) {
		return dtoOpt(repo.findById(id), UserName.class);
	}

	@Override
	public UserNamePict getUserNamePictById(String id) {
		return dtoOpt(repo.findById(id), UserNamePict.class);
	}

	// ajout pour userSettings
	@Override
	public UserSettings getUserSettingsById(String id) {
		return dtoOpt(repo.findById(id), UserSettings.class);
	}

	@Override
	public UserNamePict getDefaultUser() {
		return dtoOpt(repo.findAll().stream().filter(user -> user.getFirstname().equals("Félix")).findFirst(),
				UserNamePict.class);
	}

	@Override
	public User addUser(UserCreate userCreate) {
		User user = dto(userCreate, User.class);
		user.setCreation(LocalDate.now());
		return this.repo.save(user);
	}

	@Override
	public List<UserNamePict> getUserNotInTheTeam(String teamId) {
		List<User> users = this.repo.findAll();
		List<User> userIntheTeam = getUserByTeamId(teamId);
		return users.stream()
				.filter(user -> !userIntheTeam.contains(user))
				.map(user -> dto(user,UserNamePict.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<User> getUserByTeamId(String teamId){
		List<Member> membersInTheTeam = this.memberService.findMemberByTeamId(teamId);
		List<User> userIntheTeam = new ArrayList<User>();
		for (Member member : membersInTheTeam) {
			userIntheTeam.add(member.getUser());
		}
		return userIntheTeam;
	}
}
