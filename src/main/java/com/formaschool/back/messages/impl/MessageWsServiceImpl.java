package com.formaschool.back.messages.impl;

import java.time.LocalDateTime;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.files.FileService;
import com.formaschool.back.files.Folder;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.members.MemberService;
import com.formaschool.back.messages.Message;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.messages.dto.MessageDelete;
import com.formaschool.back.messages.dto.MessageEdit;
import com.formaschool.back.messages.dto.MessageSend;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.messages.services.MessageBackService;
import com.formaschool.back.messages.services.MessageWsService;
import com.formaschool.back.salons.SalonService;

public class MessageWsServiceImpl implements MessageWsService {

	private MessageRepository repo;
	private MessageBackService back;
	private Utils utils;
	private final Logger LOGGER;

	private MemberService memberService;
	private SalonService salonService;
	private FileService fileService;

	public MessageWsServiceImpl(MessageRepository repo, Utils utils, MemberService memberService,
			SalonService salonService, FileService fileService, MessageBackService back) {
		this.repo = repo;
		this.back = back;
		LOGGER = utils.getLogger("MessageService");

		this.utils = utils;
		this.memberService = memberService;
		this.salonService = salonService;
		this.fileService = fileService;
	}

	// ====================================================================================================

	@Override
	public MessageWithReacts sendMessage(MessageSend msg) {
		Message entity = new Message(memberService.get(msg.getMemberId()), salonService.get(msg.getSalonId()),
				msg.getContent(), fileService.upload(Folder.SHARED_FILES, msg.getFileName(), msg.getFile()),
				LocalDateTime.now(), null);
		repo.save(entity);
		LOGGER.info("Send message: " + entity);
		if (entity.getFile() != null)
			LOGGER.info("Send File: " + entity.getFile().getName());
		return back.toMessageWithReacts(entity);
	}

	@Override
	public MessageWithReacts editMessage(MessageEdit msg) {
		Message entity = utils.opt(repo.findById(msg.getId()));
		entity.setContent(msg.getContent());
		return back.toMessageWithReacts(repo.save(entity));
	}

	@Override
	public MessageDelete deleteMessage(String msgId) {
		// TODO Remove file, reactions, ...
		Message entity = utils.opt(repo.findById(msgId));
		repo.deleteById(msgId);
		LOGGER.warn("Delete message: " + entity);
		return new MessageDelete(entity.getSalon().getId(), msgId);
	}
}
