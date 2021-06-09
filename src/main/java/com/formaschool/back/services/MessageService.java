package com.formaschool.back.services;

import com.formaschool.back.dto.MessageWithReacts;
import com.formaschool.back.models.Message;

public interface MessageService extends CRUDService<Message> {
	public MessageWithReacts getMessageWithReaction(String id);
}
