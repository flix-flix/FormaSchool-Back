package com.formaschool.back.messages.services;

import com.formaschool.back.messages.Message;
import com.formaschool.back.messages.dto.MessageWithReacts;

public interface MessageBackService {
	public MessageWithReacts toMessageWithReacts(Message entity);
}
