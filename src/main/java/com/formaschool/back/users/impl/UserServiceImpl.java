package com.formaschool.back.users.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back._init.InitController;
import com.formaschool.back._utils.Utils;
import com.formaschool.back.files.FileService;
import com.formaschool.back.files.Folder;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.members.Member;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.members.dto.MemberDTO;
import com.formaschool.back.users.User;
import com.formaschool.back.users.UserRepository;
import com.formaschool.back.users.dto.UserConnect;
import com.formaschool.back.users.dto.UserCreate;
import com.formaschool.back.users.dto.UserCreateWithFile;
import com.formaschool.back.users.dto.UserLocalStorage;
import com.formaschool.back.users.dto.UserNamePict;
import com.formaschool.back.users.services.UserService;

public class UserServiceImpl extends CRUDServiceImpl<User> implements UserService {

	@Autowired
	private InitController init;
	private boolean isInit = false;

	private UserRepository repo;
	private MemberService memberService;
	private FileService fileService;
	private LogService logService;

	private final Logger LOGGER;

	// ====================================================================================================

	public UserServiceImpl(UserRepository repo, Utils utils, MemberService memberService, FileService fileService,
			LogService logService) {
		super(repo, utils);
		this.repo = repo;
		LOGGER = utils.getLogger("UserService");
		this.memberService = memberService;
		this.fileService = fileService;
		this.logService = logService;
	}

	// ====================================================================================================

	@Override
	public UserNamePict getUserNamePictById(String id) {
		return dto(opt(repo.findById(id)), UserNamePict.class);
	}

	@Override
	public User addUser(UserCreate userCreate, String idAddedBy) {
		User user = dto(userCreate, User.class);
		LOGGER.info("Create user: " + user);
		user.setCreation(LocalDate.now());
		this.logService.addUserLog(user.getFirstname(), user.getLastname(), idAddedBy);
		return this.repo.save(user);
	}

	@Override
	public List<UserNamePict> getUserNotInTheTeam(String teamId) {
		List<User> users = this.repo.findAll();
		List<User> userIntheTeam = getUserByTeamId(teamId);
		return users.stream().filter(user -> !userIntheTeam.contains(user)).map(user -> dto(user, UserNamePict.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getUserByTeamId(String teamId) {
		List<Member> membersInTheTeam = this.memberService.findByTeamId(teamId);
		List<User> userIntheTeam = new ArrayList<User>();
		for (Member member : membersInTheTeam) {
			userIntheTeam.add(member.getUser());
		}
		return userIntheTeam;
	}

	@Override
	public UserLocalStorage connect(UserConnect connect) {
		initMongo();

		User entity = opt(repo.findByEmail(connect.getEmail()));
		if (!entity.getPassword().equals(convertPwd(connect.getPassword()))) {
			LOGGER.warn("Wrong mdp: " + connect.getEmail());
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		LOGGER.info("User connect: " + connect.getEmail());

		UserLocalStorage dto = dto(entity, UserLocalStorage.class);
		dto.setMembers(memberService.findAllByUserId(entity.getId()).stream()
				.map(member -> dto(member, MemberDTO.class)).collect(Collectors.toList()));
		return dto;
	}

	// ====================================================================================================

	public String convertPwd(String pwd) {
		// TODO [Improve]
		// TODO Call on save/update user
		return pwd;
	}

	// ====================================================================================================

	public void initMongo() {
		if (isInit)
			return;
		try {
			init.drop();
			init.init();
			isInit = true;
		} catch (ResponseStatusException e) {
		}
	}

	@Override
	public User saveWithFile(UserCreateWithFile user, String idAddedBy) {
		// TODO BirthDate + Phone
		User entity;
		if (user.getFile() != null) {
			entity = new User(user.getFirstname(), user.getLastname(), user.getPassword(), user.getEmail(),
					fileService.upload(Folder.USERS, user.getFilename(), user.getFile()), null, "0612345678",
					LocalDate.now());
		} else {
			entity = new User(user.getFirstname(), user.getLastname(), user.getPassword(), user.getEmail(), null, null,
					"0612345678", LocalDate.now());
		}
		this.logService.addUserLog(user.getFirstname(), user.getLastname(), idAddedBy);
		return repo.save(entity);
	}

}
