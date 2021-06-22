package com.formaschool.back.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.logging.LoggerFactory;
import com.formaschool.back.repositories.EmojiRepository;
import com.formaschool.back.repositories.FileRepository;
import com.formaschool.back.repositories.LogRepository;
import com.formaschool.back.repositories.MemberRepository;
import com.formaschool.back.repositories.MessageRepository;
import com.formaschool.back.repositories.PermissionRepository;
import com.formaschool.back.repositories.ReactionRepository;
import com.formaschool.back.repositories.RoleRepository;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.repositories.TeamRepository;
import com.formaschool.back.repositories.TeamSalonRightsRepository;
import com.formaschool.back.repositories.UserRepository;
import com.formaschool.back.services.EmojiService;
import com.formaschool.back.services.FileService;
import com.formaschool.back.services.LogService;
import com.formaschool.back.services.MemberService;
import com.formaschool.back.services.MessageService;
import com.formaschool.back.services.PermissionService;
import com.formaschool.back.services.ReactionService;
import com.formaschool.back.services.RoleService;
import com.formaschool.back.services.SalonService;
import com.formaschool.back.services.TeamSalonRightsService;
import com.formaschool.back.services.TeamService;
import com.formaschool.back.services.UserService;
import com.formaschool.back.services.impl.EmojiServiceImpl;
import com.formaschool.back.services.impl.FileServiceImpl;
import com.formaschool.back.services.impl.LogServiceImpl;
import com.formaschool.back.services.impl.MemberServiceImpl;
import com.formaschool.back.services.impl.MessageServiceImpl;
import com.formaschool.back.services.impl.PermissionServiceImpl;
import com.formaschool.back.services.impl.ReactionServiceImpl;
import com.formaschool.back.services.impl.RoleServiceImpl;
import com.formaschool.back.services.impl.SalonServiceImpl;
import com.formaschool.back.services.impl.TeamSalonRightsServiceImpl;
import com.formaschool.back.services.impl.TeamServiceImpl;
import com.formaschool.back.services.impl.UserServiceImpl;

@Configuration
public class ServiceConfiguration {

	@Bean
	public UserService userService(UserRepository repo, MemberService memberService, ObjectMapper mapper,
			LoggerFactory logger) {
		return new UserServiceImpl(repo, mapper, logger, memberService);
	}

	@Bean
	public TeamService teamService(TeamRepository repo, ObjectMapper mapper, MemberService member,
			SalonService salonService) {
		return new TeamServiceImpl(repo, mapper, member, salonService);
	}

	@Bean
	public SalonService salonService(SalonRepository repo, ObjectMapper mapper) {
		return new SalonServiceImpl(repo, mapper);
	}

	@Bean
	public MessageService msgService(MessageRepository repo, ObjectMapper mapper, LoggerFactory logger,
			MemberService member, SalonService salon, FileService file, ReactionService react) {
		return new MessageServiceImpl(repo, mapper, logger, member, salon, file, react);
	}

	@Bean
	public MemberService memberService(MemberRepository repo, PermissionService permissionService,
			SalonService salonService, ObjectMapper mapper) {
		return new MemberServiceImpl(repo, permissionService, salonService, mapper);
	}

	@Bean
	public EmojiService emojiService(EmojiRepository repo, TeamService teamService, UserService userService,
			ObjectMapper mapper, LoggerFactory logger) {
		return new EmojiServiceImpl(repo, mapper, logger, teamService, userService);
	}

	@Bean
	public LogService logService(LogRepository repository, ObjectMapper mapper) {
		return new LogServiceImpl(repository, mapper);
	}

	@Bean
	public ReactionService reactionService(ReactionRepository repo, ObjectMapper mapper) {
		return new ReactionServiceImpl(repo, mapper);
	}

	@Bean
	public RoleService roleService(RoleRepository repo, SalonService salonService, PermissionService permissionService,
			TeamService teamService, ObjectMapper mapper) {
		return new RoleServiceImpl(repo, salonService, permissionService, teamService, mapper);
	}

	@Bean
	public PermissionService permissionService(PermissionRepository repo, ObjectMapper mapper) {
		return new PermissionServiceImpl(repo, mapper);
	}

	@Bean
	public TeamSalonRightsService teamSalonRightsService(TeamSalonRightsRepository repo, ObjectMapper mapper) {
		return new TeamSalonRightsServiceImpl(repo, mapper);
	}

	@Bean
	public FileService fileService(FileRepository repo, ObjectMapper mapper) {
		return new FileServiceImpl(repo, mapper);
	}
}
