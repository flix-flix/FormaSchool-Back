package com.formaschool.back.messages.impl;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.messages.Message;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.messages.services.MessageBackService;
import com.formaschool.back.reactions.services.ReactionBackService;

public class MessageBackServiceImpl implements MessageBackService {

	private MessageRepository repo;
	private Utils utils;

	private ReactionBackService reactService;

	public MessageBackServiceImpl(MessageRepository repo, Utils utils, ReactionBackService reactService) {
		this.repo = repo;
		this.utils = utils;
		this.reactService = reactService;

		this.repo.equals(null);
	}

	// ====================================================================================================

	@Override
	public MessageWithReacts toMessageWithReacts(Message entity) {
		MessageWithReacts dto = utils.dto(entity, MessageWithReacts.class);
		dto.setReactions(reactService.getAllReactionsUsersOfMessage(entity.getId()));
		dto.setTeamId(entity.getSalon().getTeam().getId());
		dto.setSalonId(entity.getSalon().getId());
		return dto;
	}
}
