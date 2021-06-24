package com.formaschool.back.messages;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.messages.dto.MessageDelete;
import com.formaschool.back.messages.dto.MessageEdit;
import com.formaschool.back.messages.dto.MessageSendString;
import com.formaschool.back.messages.dto.MessageWithReacts;

public interface MessageService extends CRUDService<Message> {
	public MessageWithReacts getMessageWithReacts(String msgId);

	public List<MessageWithReacts> getAllMessageWithReactsOfSalon(String salonId);

	public MessageWithReacts sendMessage(MessageSendString msg);

	public MessageWithReacts editMessage(MessageEdit msg);

	public MessageDelete deleteMessage(String msgId);
}
