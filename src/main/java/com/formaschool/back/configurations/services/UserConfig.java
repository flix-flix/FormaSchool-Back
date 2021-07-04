package com.formaschool.back.configurations.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.files.FileService;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.members.MemberRepository;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.members.MemberServiceImpl;
import com.formaschool.back.permissions.PermissionService;
import com.formaschool.back.roles.RoleService;
import com.formaschool.back.salons.SalonService;
import com.formaschool.back.teams.impl.TeamMemberServiceImpl;
import com.formaschool.back.teams.services.TeamMemberService;
import com.formaschool.back.users.UserRepository;
import com.formaschool.back.users.impl.UserServiceImpl;
import com.formaschool.back.users.impl.UserSettingsServiceImpl;
import com.formaschool.back.users.services.UserService;
import com.formaschool.back.users.services.UserSettingsService;

@Configuration
public class UserConfig {

	@Bean
	public UserService userService(UserRepository repo, MemberService memberService, Utils utils,
			FileService fileService, LogService logService) {
		return new UserServiceImpl(repo, utils, memberService, fileService, logService);
	}

	@Bean
	public UserSettingsService userSettingsService(UserRepository repo, Utils utils, FileService file) {
		return new UserSettingsServiceImpl(repo, utils, file);
	}

	@Bean
	public MemberService memberService(MemberRepository repo, PermissionService permissionService,
			RoleService roleService, SalonService salonService, Utils utils) {
		return new MemberServiceImpl(repo, utils, permissionService, salonService, roleService);
	}

	@Bean
	public TeamMemberService teamMemberService(Utils utils, MemberService member) {
		return new TeamMemberServiceImpl(utils, member);
	}
}
