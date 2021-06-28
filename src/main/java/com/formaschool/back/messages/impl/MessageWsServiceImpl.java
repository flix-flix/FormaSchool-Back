package com.formaschool.back.messages.impl;

import static com.formaschool.back._utils.Utils.dto;
import static com.formaschool.back._utils.Utils.opt;

import java.time.LocalDateTime;

import com.formaschool.back.files.FileService;
import com.formaschool.back.files.Folder;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.logging.LoggerFactory;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.messages.Message;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.messages.dto.MessageDelete;
import com.formaschool.back.messages.dto.MessageEdit;
import com.formaschool.back.messages.dto.MessageSendString;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.messages.services.MessageWsService;
import com.formaschool.back.reactions.ReactionService;
import com.formaschool.back.salons.SalonService;

public class MessageWsServiceImpl implements MessageWsService {

	private MessageRepository repo;
	private final Logger LOGGER;

	private MemberService memberService;
	private SalonService salonService;
	private FileService fileService;
	private ReactionService reactService;

	public MessageWsServiceImpl(MessageRepository repo, LoggerFactory factory, MemberService memberService,
			SalonService salonService, FileService fileService, ReactionService reactService) {
		this.repo = repo;
		LOGGER = factory.getElasticLogger("MessageService");

		this.repo = repo;
		this.memberService = memberService;
		this.salonService = salonService;
		this.fileService = fileService;
		this.reactService = reactService;
	}

	// ====================================================================================================

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
	public MessageWithReacts editMessage(MessageEdit msg) {
		Message entity = opt(repo.findById(msg.getId()));
		entity.setContent(msg.getContent());
		return toMessageWithReacts(repo.save(entity));
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
