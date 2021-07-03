package com.formaschool.back.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.emojis.EmojiRepository;
import com.formaschool.back.emojis.EmojiService;
import com.formaschool.back.emojis.EmojiServiceImpl;
import com.formaschool.back.logs.LogRepository;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.logs.LogServiceImpl;
import com.formaschool.back.permissions.PermissionRepository;
import com.formaschool.back.permissions.PermissionService;
import com.formaschool.back.permissions.PermissionServiceImpl;
import com.formaschool.back.rights.TeamSalonRightsRepository;
import com.formaschool.back.rights.TeamSalonRightsService;
import com.formaschool.back.rights.TeamSalonRightsServiceImpl;
import com.formaschool.back.roles.RoleRepository;
import com.formaschool.back.roles.RoleService;
import com.formaschool.back.roles.RoleServiceImpl;
import com.formaschool.back.salons.SalonService;
import com.formaschool.back.teams.services.TeamService;
import com.formaschool.back.users.services.UserService;

@Configuration
public class AdminConfig {

	@Bean
	public LogService logService(LogRepository repository, Utils utils) {
		return new LogServiceImpl(repository, utils);
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
	public EmojiService emojiService(EmojiRepository repo, Utils utils, TeamService teamService,
			UserService userService, LogService logService) {
		return new EmojiServiceImpl(repo, utils, teamService, userService, logService);
	}
}
