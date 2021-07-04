package com.formaschool.back.messages.services;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.messages.Message;
import com.formaschool.back.messages.dto.MessageWithReacts;

public interface MessageService extends CRUDService<Message> {

	public MessageWithReacts getMessageWithReacts(String msgId);

	public List<MessageWithReacts> getAllMessageWithReactsOfSalon(String salonId);
}
