package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

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

	// ====================================================================================================
	// Service

	@Override
	public MessageWithReacts getMessageWithReacts(String msgId) {
		return toMessageWithReacts(opt(repo.findById(msgId)));
	}

	@Override
	public List<MessageWithReacts> getAllMessageWithReactsOfSalon(String salonId) {
		return repo.findBySalonId(salonId).stream().map(msg -> toMessageWithReacts(msg)).collect(Collectors.toList());
	}

	// ====================================================================================================

	private MessageWithReacts toMessageWithReacts(Message entity) {
		MessageWithReacts dto = dto(entity, MessageWithReacts.class);
		System.out.println(entity.getFile());
		dto.setReactions(reactService.getAllReactionsUsersOfMessage(entity.getId()));
		return dto;
	}
}
