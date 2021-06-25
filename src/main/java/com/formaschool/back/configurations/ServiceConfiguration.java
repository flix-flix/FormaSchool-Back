package com.formaschool.back.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.emojis.EmojiRepository;
import com.formaschool.back.emojis.EmojiService;
import com.formaschool.back.emojis.EmojiServiceImpl;
import com.formaschool.back.files.FileRepository;
import com.formaschool.back.files.FileService;
import com.formaschool.back.files.FileServiceImpl;
import com.formaschool.back.logging.LoggerFactory;
import com.formaschool.back.logs.LogRepository;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.logs.LogServiceImpl;
import com.formaschool.back.members.MemberRepository;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.members.MemberServiceImpl;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.messages.MessageService;
import com.formaschool.back.messages.MessageServiceImpl;
import com.formaschool.back.permissions.PermissionRepository;
import com.formaschool.back.permissions.PermissionService;
import com.formaschool.back.permissions.PermissionServiceImpl;
import com.formaschool.back.reactions.ReactionRepository;
import com.formaschool.back.reactions.ReactionService;
import com.formaschool.back.reactions.ReactionServiceImpl;
import com.formaschool.back.rights.TeamSalonRightsRepository;
import com.formaschool.back.rights.TeamSalonRightsService;
import com.formaschool.back.rights.TeamSalonRightsServiceImpl;
import com.formaschool.back.roles.RoleRepository;
import com.formaschool.back.roles.RoleService;
import com.formaschool.back.roles.RoleServiceImpl;
import com.formaschool.back.salons.SalonRepository;
import com.formaschool.back.salons.SalonService;
import com.formaschool.back.salons.SalonServiceImpl;
import com.formaschool.back.teams.TeamRepository;
import com.formaschool.back.teams.TeamService;
import com.formaschool.back.teams.TeamServiceImpl;
import com.formaschool.back.teams.services.TeamMemberService;
import com.formaschool.back.teams.services.TeamMemberServiceImpl;
import com.formaschool.back.users.UserRepository;
import com.formaschool.back.users.UserService;
import com.formaschool.back.users.UserServiceImpl;

@Configuration
public class ServiceConfiguration {

	@Bean
	public UserService userService(UserRepository repo, MemberService memberService, ObjectMapper mapper,
			LoggerFactory logger, FileService fileService) {
		return new UserServiceImpl(repo, mapper, logger, memberService, fileService);
	}

	@Bean
	public TeamService teamService(TeamRepository repo, ObjectMapper mapper, SalonService salonService,
			FileService fileService) {
		return new TeamServiceImpl(repo, mapper, salonService, fileService);
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
			RoleService roleService, SalonService salonService, ObjectMapper mapper) {
		return new MemberServiceImpl(repo, permissionService, salonService, roleService, mapper);
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

	@Bean
	public TeamMemberService teamMemberService(ObjectMapper mapper, MemberService member) {
		return new TeamMemberServiceImpl(mapper, member);
	}
}
