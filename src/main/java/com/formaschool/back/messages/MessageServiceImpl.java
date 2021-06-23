package com.formaschool.back.messages;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back.files.FileService;
import com.formaschool.back.files.Folder;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.logging.LoggerFactory;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.messages.dto.MessageDelete;
import com.formaschool.back.messages.dto.MessageSendString;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.reactions.ReactionService;
import com.formaschool.back.salons.SalonService;

public class MessageServiceImpl extends CRUDServiceImpl<Message> implements MessageService {

	private MessageRepository repo;
	private final Logger LOGGER;

	private MemberService memberService;
	private SalonService salonService;
	private FileService fileService;
	private ReactionService reactService;

	public MessageServiceImpl(MessageRepository repo, ObjectMapper mapper, LoggerFactory factory,
			MemberService memberService, SalonService salonService, FileService fileService,
			ReactionService reactService) {
		super(repo, mapper);

		LOGGER = factory.getElasticLogger("MessageService");

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

	// ====================================================================================================
	// WebSocket

	@Override
	public MessageWithReacts sendMessage(MessageSendString msg) {
		Message entity = new Message(memberService.get(msg.getMemberId()), salonService.get(msg.getSalonId()),
				msg.getContent(), fileService.upload(Folder.SHARED_FILES, msg.getFileName(), msg.getFile()),
				LocalDateTime.now(), null);
		repo.save(entity);
		LOGGER.info("Send message: " + entity);
		if (entity.getFile() != null)
			LOGGER.info("Send File: " + entity.getFile().getName());
		return toMessageWithReacts(entity);
	}

	@Override
	public MessageDelete deleteMessage(String msgId) {
		// TODO Remove file, reactions, ...
		Message entity = opt(repo.findById(msgId));
		repo.deleteById(msgId);
		LOGGER.warn("Delete message: " + entity);
		return new MessageDelete(entity.getSalon().getId(), msgId);
	}

	// ====================================================================================================

	private MessageWithReacts toMessageWithReacts(Message entity) {
		MessageWithReacts dto = dto(entity, MessageWithReacts.class);
		dto.setReactions(reactService.getAllReactionsUsersOfMessage(entity.getId()));
		dto.setSalonId(entity.getSalon().getId());
		return dto;
	}
}
