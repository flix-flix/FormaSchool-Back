package com.formaschool.back.messages.impl;

import static com.formaschool.back._utils.Utils.dto;
import static com.formaschool.back._utils.Utils.opt;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back.messages.Message;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.messages.services.MessageService;
import com.formaschool.back.reactions.ReactionService;

public class MessageServiceImpl extends CRUDServiceImpl<Message> implements MessageService {

	private MessageRepository repo;

	private ReactionService reactService;

	public MessageServiceImpl(MessageRepository repo, ObjectMapper mapper, ReactionService reactService) {
		super(repo, mapper);

		this.repo = repo;
		this.reactService = reactService;
	}

	// ====================================================================================================

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
		dto.setReactions(reactService.getAllReactionsUsersOfMessage(entity.getId()));
		dto.setSalonId(entity.getSalon().getId());
		return dto;
	}
}