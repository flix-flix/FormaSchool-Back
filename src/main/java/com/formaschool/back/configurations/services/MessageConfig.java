package com.formaschool.back.configurations.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.files.FileService;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.messages.impl.MessageBackServiceImpl;
import com.formaschool.back.messages.impl.MessageServiceImpl;
import com.formaschool.back.messages.impl.MessageWsServiceImpl;
import com.formaschool.back.messages.services.MessageBackService;
import com.formaschool.back.messages.services.MessageService;
import com.formaschool.back.messages.services.MessageWsService;
import com.formaschool.back.priv.PrivateService;
import com.formaschool.back.priv.PrivateServiceImpl;
import com.formaschool.back.reactions.ReactionRepository;
import com.formaschool.back.reactions.impl.ReactionBackServiceImpl;
import com.formaschool.back.reactions.impl.ReactionServiceImpl;
import com.formaschool.back.reactions.services.ReactionBackService;
import com.formaschool.back.reactions.services.ReactionService;
import com.formaschool.back.salons.SalonService;
import com.formaschool.back.teams.services.TeamService;

@Configuration
public class MessageConfig {

	// ====================================================================================================
	// Messages

	@Bean
	public MessageBackService messageBackService(MessageRepository repo, Utils utils, ReactionBackService react) {
		return new MessageBackServiceImpl(repo, utils, react);
	}

	@Bean
	public MessageService messageService(MessageRepository repo, Utils utils, MessageBackService back) {
		return new MessageServiceImpl(repo, utils, back);
	}

	@Bean
	public MessageWsService messageWsService(MessageRepository repo, Utils utils, MemberService member,
			SalonService salon, FileService file, MessageBackService back) {
		return new MessageWsServiceImpl(repo, utils, member, salon, file, back);
	}

	// ====================================================================================================
	// Reactions

	@Bean
	public ReactionBackService reactionBackService(ReactionRepository repo, Utils utils) {
		return new ReactionBackServiceImpl(repo, utils);
	}

	@Bean
	public ReactionService reactionService(ReactionRepository repo, Utils utils, MessageService message) {
		return new ReactionServiceImpl(repo, utils, message);
	}

	// ====================================================================================================

	@Bean
	public PrivateService privateService(MemberService member, TeamService team, SalonService salon) {
		return new PrivateServiceImpl(member, team, salon);
	}
}
