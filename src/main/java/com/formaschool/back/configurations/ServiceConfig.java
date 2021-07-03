package com.formaschool.back.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.files.FileRepository;
import com.formaschool.back.files.FileService;
import com.formaschool.back.files.FileServiceImpl;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.messages.services.MessageService;
import com.formaschool.back.salons.SalonRepository;
import com.formaschool.back.salons.SalonService;
import com.formaschool.back.salons.SalonServiceImpl;
import com.formaschool.back.teams.TeamRepository;
import com.formaschool.back.teams.impl.TeamServiceImpl;
import com.formaschool.back.teams.services.TeamService;

@Configuration
public class ServiceConfig {

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
	public FileService fileService(FileRepository repo, Utils utils) {
		return new FileServiceImpl(repo, utils);
	}
}
