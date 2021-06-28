package com.formaschool.back.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.emojis.EmojiRepository;
import com.formaschool.back.emojis.EmojiService;
import com.formaschool.back.emojis.EmojiServiceImpl;
import com.formaschool.back.files.FileRepository;
import com.formaschool.back.files.FileService;
import com.formaschool.back.files.FileServiceImpl;
import com.formaschool.back.logs.LogRepository;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.logs.LogServiceImpl;
import com.formaschool.back.members.MemberRepository;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.members.MemberServiceImpl;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.messages.impl.MessageServiceImpl;
import com.formaschool.back.messages.impl.MessageWsServiceImpl;
import com.formaschool.back.messages.services.MessageService;
import com.formaschool.back.messages.services.MessageWsService;
import com.formaschool.back.permissions.PermissionRepository;
import com.formaschool.back.permissions.PermissionService;
import com.formaschool.back.permissions.PermissionServiceImpl;
import com.formaschool.back.priv.PrivateService;
import com.formaschool.back.priv.PrivateServiceImpl;
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
import com.formaschool.back.teams.impl.TeamMemberServiceImpl;
import com.formaschool.back.teams.impl.TeamServiceImpl;
import com.formaschool.back.teams.services.TeamMemberService;
import com.formaschool.back.teams.services.TeamService;
import com.formaschool.back.users.UserRepository;
import com.formaschool.back.users.UserService;
import com.formaschool.back.users.UserServiceImpl;

@Configuration
public class ServiceConfiguration {

	@Bean
	public UserService userService(UserRepository repo, MemberService memberService, Utils utils,
			FileService fileService, LogService logService) {
		return new UserServiceImpl(repo, utils, memberService, fileService, logService);
	}

	@Bean
	public TeamService teamService(TeamRepository repo, Utils utils, SalonService salonService, FileService fileService,
			LogService logService) {
		return new TeamServiceImpl(repo, utils, salonService, fileService, logService);
	}

	@Bean
	public SalonService salonService(SalonRepository repo, Utils utils, LogService logService, MessageService message) {
		return new SalonServiceImpl(repo, utils, logService, message);
	}

	@Bean
	public MessageService msgService(MessageRepository repo, Utils utils, ReactionService react) {
		return new MessageServiceImpl(repo, utils, react);
	}

	@Bean
	public MemberService memberService(MemberRepository repo, PermissionService permissionService,
			RoleService roleService, SalonService salonService, Utils utils) {
		return new MemberServiceImpl(repo, utils, permissionService, salonService, roleService);
	}

	@Bean
	public EmojiService emojiService(EmojiRepository repo, Utils utils, TeamService teamService,
			UserService userService, LogService logService) {
		return new EmojiServiceImpl(repo, utils, teamService, userService, logService);
	}

	@Bean
	public LogService logService(LogRepository repository, Utils utils) {
		return new LogServiceImpl(repository, utils);
	}

	@Bean
	public ReactionService reactionService(ReactionRepository repo, Utils utils) {
		return new ReactionServiceImpl(repo, utils);
	}

	@Bean
	public RoleService roleService(RoleRepository repo, SalonService salonService, PermissionService permissionService,
			TeamService teamService, Utils utils) {
		return new RoleServiceImpl(repo, utils, salonService, permissionService, teamService);
	}

	@Bean
	public PermissionService permissionService(PermissionRepository repo, Utils utils) {
		return new PermissionServiceImpl(repo, utils);
	}

	@Bean
	public TeamSalonRightsService teamSalonRightsService(TeamSalonRightsRepository repo, Utils utils) {
		return new TeamSalonRightsServiceImpl(repo, utils);
	}

	@Bean
	public FileService fileService(FileRepository repo, Utils utils) {
		return new FileServiceImpl(repo, utils);
	}

	@Bean
	public TeamMemberService teamMemberService(Utils utils, MemberService member) {
		return new TeamMemberServiceImpl(utils, member);
	}

	@Bean
	public PrivateService privateService(MemberService member, TeamService team, SalonService salon) {
		return new PrivateServiceImpl(member, team, salon);
	}

	@Bean
	public MessageWsService messageWsService(MessageRepository repo, Utils utils, MemberService member,
			SalonService salon, FileService file, ReactionService react) {
		return new MessageWsServiceImpl(repo, utils, member, salon, file, react);
	}
}
