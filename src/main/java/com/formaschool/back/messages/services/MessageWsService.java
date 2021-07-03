package com.formaschool.back.messages.services;

import com.formaschool.back.messages.dto.MessageDelete;
import com.formaschool.back.messages.dto.MessageEdit;
import com.formaschool.back.messages.dto.MessageSend;
import com.formaschool.back.messages.dto.MessageWithReacts;

public interface MessageWsService {
	public MessageWithReacts sendMessage(MessageSend msg);

	public MessageWithReacts editMessage(MessageEdit msg);

	public MessageDelete deleteMessage(String msgId);
}
