package com.formaschool.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.Message;
import com.formaschool.back.repositories.MessageRepository;
import com.formaschool.back.services.MessageService;

public class MessageServiceImpl extends CRUDServiceImpl<Message> implements MessageService {

	private MessageRepository repo;

	public MessageServiceImpl(MessageRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}
}
