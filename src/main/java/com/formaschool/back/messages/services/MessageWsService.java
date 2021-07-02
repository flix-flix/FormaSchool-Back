package com.formaschool.back.messages.services;

import com.formaschool.back.messages.dto.MessageDelete;
import com.formaschool.back.messages.dto.MessageEdit;
import com.formaschool.back.messages.dto.MessageSendString;
import com.formaschool.back.messages.dto.MessageWithReacts;

public interface MessageWsService {
	public MessageWithReacts sendMessage(MessageSendString msg);

	public MessageWithReacts editMessage(MessageEdit msg);

	public MessageDelete deleteMessage(String msgId);
}
