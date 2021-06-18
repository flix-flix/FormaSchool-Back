package com.formaschool.back.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.messages.MessageSend;
import com.formaschool.back.dto.messages.MessageWithReacts;
import com.formaschool.back.models.Message;
import com.formaschool.back.repositories.MessageRepository;
import com.formaschool.back.services.FileService;
import com.formaschool.back.services.MemberService;
import com.formaschool.back.services.MessageService;
import com.formaschool.back.services.ReactionService;
import com.formaschool.back.services.SalonService;

public class MessageServiceImpl extends CRUDServiceImpl<Message> implements MessageService {

	private MessageRepository repo;

	private MemberService memberService;
	private SalonService salonService;
	private FileService fileService;
	private ReactionService reactService;

	public MessageServiceImpl(MessageRepository repo, ObjectMapper mapper, MemberService memberService,
			SalonService salonService, FileService fileService, ReactionService reactService) {
		super(repo, mapper);
		this.repo = repo;
		this.memberService = memberService;
		this.salonService = salonService;
		this.fileService = fileService;
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

	@Override
	public MessageWithReacts restSendMsg(MessageSend dto) {
		Message entity = new Message(memberService.get(dto.getMemberId()), salonService.get(dto.getSalonId()),
				dto.getContent(), fileService.saveMultiPartFile(dto.getFile()), LocalDateTime.now(), null);
		repo.save(entity);
		return dto(entity, MessageWithReacts.class);
	}

	// ====================================================================================================
	// WebSocket

	@Override
	public MessageWithReacts sendMessage(MessageSend msg) {
		Message entity = new Message(memberService.get(msg.getMemberId()), salonService.get(msg.getSalonId()),
				msg.getContent(), null, LocalDateTime.now(), null);
		repo.save(entity);
		return dto(entity, MessageWithReacts.class);
	}

	// ====================================================================================================

	private MessageWithReacts toMessageWithReacts(Message entity) {
		MessageWithReacts dto = dto(entity, MessageWithReacts.class);
		dto.setReactions(reactService.getAllReactionsUsersOfMessage(entity.getId()));
		return dto;
	}
}
