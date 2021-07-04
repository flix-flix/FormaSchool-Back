package com.formaschool.back.messages.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back._utils.Utils;
import com.formaschool.back.messages.Message;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.messages.services.MessageBackService;
import com.formaschool.back.messages.services.MessageService;

public class MessageServiceImpl extends CRUDServiceImpl<Message> implements MessageService {

	private MessageRepository repo;
	private MessageBackService back;

	public MessageServiceImpl(MessageRepository repo, Utils utils, MessageBackService back) {
		super(repo, utils);

		this.repo = repo;
		this.back = back;
	}

	// ====================================================================================================

	@Override
	public MessageWithReacts getMessageWithReacts(String msgId) {
		return back.toMessageWithReacts(opt(repo.findById(msgId)));
	}

	@Override
	public List<MessageWithReacts> getAllMessageWithReactsOfSalon(String salonId) {
		return repo.findBySalonId(salonId).stream().map(msg -> back.toMessageWithReacts(msg))
				.collect(Collectors.toList());
	}
}
