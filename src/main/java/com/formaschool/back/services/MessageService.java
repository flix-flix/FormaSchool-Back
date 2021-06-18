package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.messages.MessageSend;
import com.formaschool.back.dto.messages.MessageWithReacts;
import com.formaschool.back.models.Message;

public interface MessageService extends CRUDService<Message> {
	public MessageWithReacts getMessageWithReacts(String msgId);

	public List<MessageWithReacts> getAllMessageWithReactsOfSalon(String salonId);

	public MessageWithReacts sendMessage(MessageSend msg);

	public MessageWithReacts restSendMsg(MessageSend msg);
}
