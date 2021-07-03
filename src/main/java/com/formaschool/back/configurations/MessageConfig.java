package com.formaschool.back.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.files.FileService;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.messages.impl.MessageServiceImpl;
import com.formaschool.back.messages.impl.MessageWsServiceImpl;
import com.formaschool.back.messages.services.MessageService;
import com.formaschool.back.messages.services.MessageWsService;
import com.formaschool.back.priv.PrivateService;
import com.formaschool.back.priv.PrivateServiceImpl;
import com.formaschool.back.reactions.ReactionRepository;
import com.formaschool.back.reactions.ReactionService;
import com.formaschool.back.reactions.ReactionServiceImpl;
import com.formaschool.back.salons.SalonService;
import com.formaschool.back.teams.services.TeamService;

@Configuration
public class MessageConfig {

	@Bean
	public MessageService msgService(MessageRepository repo, Utils utils, ReactionService react) {
		return new MessageServiceImpl(repo, utils, react);
	}

	@Bean
	public ReactionService reactionService(ReactionRepository repo, Utils utils) {
		return new ReactionServiceImpl(repo, utils);
	}

	@Bean
	public MessageWsService messageWsService(MessageRepository repo, Utils utils, MemberService member,
			SalonService salon, FileService file, ReactionService react) {
		return new MessageWsServiceImpl(repo, utils, member, salon, file, react);
	}

	@Bean
	public PrivateService privateService(MemberService member, TeamService team, SalonService salon) {
		return new PrivateServiceImpl(member, team, salon);
	}
}
