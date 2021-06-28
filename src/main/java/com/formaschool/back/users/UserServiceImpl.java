package com.formaschool.back.users;

import static com.formaschool.back._utils.Utils.dto;
import static com.formaschool.back._utils.Utils.opt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back._init.InitController;
import com.formaschool.back.files.FileService;
import com.formaschool.back.files.Folder;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.logging.LoggerFactory;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.members.Member;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.users.dto.UserConnect;
import com.formaschool.back.users.dto.UserCreate;
import com.formaschool.back.users.dto.UserCreateWithFile;
import com.formaschool.back.users.dto.UserLocalStorage;
import com.formaschool.back.users.dto.UserName;
import com.formaschool.back.users.dto.UserNamePict;
import com.formaschool.back.users.dto.UserSettings;

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

	public UserServiceImpl(UserRepository repo, ObjectMapper mapper, LoggerFactory factory, MemberService memberService,
			FileService fileService, LogService logService) {
		super(repo, mapper);
		this.repo = repo;
		LOGGER = factory.getElasticLogger("UserService");
		this.memberService = memberService;
		this.fileService = fileService;
		this.logService = logService;
	}

	// ====================================================================================================

	@Override
	public UserName getUserNameById(String id) {
		return dto(opt(repo.findById(id)), UserName.class);
	}

	@Override
	public UserNamePict getUserNamePictById(String id) {
		return dto(opt(repo.findById(id)), UserNamePict.class);
	}

	@Override
	public UserSettings getUserSettingsById(String id) {
		return dto(opt(repo.findById(id)), UserSettings.class);
	}

	@Override
	public UserSettings updateuserSettings(UserSettings dto) {
		User user = this.repo.findById(dto.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (dto.getFirstname() != null)
			user.setFirstname(dto.getFirstname());
		if (dto.getLastname() != null)
			user.setLastname(dto.getLastname());
		if (dto.getEmail() != null)
			user.setEmail(dto.getEmail());
//		if (dto.getAge() != 0)
//			user.setAge(dto.getAge());
//		if (dto.getPhone() != 0)
//			user.setPhone(dto.getPhone());
		if (dto.getPassword() != null)
			user.setPassword(dto.getPassword());

		User result = this.repo.save(user);
		return dto(result, UserSettings.class);
	}

	// userSettings Fin

	@Override
	public UserNamePict getDefaultUser() {
		return dto(opt(repo.findAll().stream().filter(user -> user.getFirstname().equals("Félix")).findFirst()),
				UserNamePict.class);
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
		dto.setMembers(memberService.findAllByUserId(entity.getId()));
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
		User entity;
		if (user.getFile() != null) {
			entity = new User(user.getFirstname(), user.getLastname(), user.getPassword(), user.getEmail(),
					fileService.upload(Folder.USERS, user.getFilename(), user.getFile()), LocalDate.now());
		} else {
			entity = new User(user.getFirstname(), user.getLastname(), user.getPassword(), user.getEmail(), null,
					LocalDate.now());
		}
		this.logService.addUserLog(user.getFirstname(), user.getLastname(), idAddedBy);
		return repo.save(entity);
	}

}
