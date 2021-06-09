package com.formaschool.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.MessageWithReacts;
import com.formaschool.back.models.Message;
import com.formaschool.back.repositories.MessageRepository;
import com.formaschool.back.services.MessageService;
import com.formaschool.back.services.ReactionService;

public class MessageServiceImpl extends CRUDServiceImpl<Message> implements MessageService {

	private MessageRepository repo;
	private ReactionService reactService;

	public MessageServiceImpl(MessageRepository repo, ObjectMapper mapper, ReactionService reactService) {
		super(repo, mapper);
		this.repo = repo;
		this.reactService = reactService;
	}

	@Override
	public MessageWithReacts getMessageWithReaction(String msgId) {
		MessageWithReacts dto = dtoOpt(repo.findById(msgId), MessageWithReacts.class);
		dto.setReactions(reactService.getReactionsUsersOfMessage(msgId));
		return dto;
	}
}
