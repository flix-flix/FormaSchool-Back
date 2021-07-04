package com.formaschool.back.messages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.messages.dto.MessageDelete;
import com.formaschool.back.messages.dto.MessageEdit;
import com.formaschool.back.messages.dto.MessageSend;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.messages.services.MessageService;
import com.formaschool.back.messages.services.MessageWsService;

@RestController
@RequestMapping("messages")
@CrossOrigin
public class MessageController implements CRUDController<Message> {
	@Autowired
	private MessageService service;
	@Autowired
	private MessageWsService serviceWs;

	@Override
	public CRUDService<Message> getGenericService() {
		return service;
	}

	// ====================================================================================================
	// REST

	@GetMapping("withReacts/{msgId}")
	public MessageWithReacts getMessageWithReactions(@PathVariable String msgId) {
		return service.getMessageWithReacts(msgId);
	}

	@GetMapping("salonWithReacts/{salonId}")
	public List<MessageWithReacts> getAllMessageWithReactionsOfSalon(@PathVariable String salonId) {
		return service.getAllMessageWithReactsOfSalon(salonId);
	}

	// ====================================================================================================
	// WebSocket

	@MessageMapping("chat.send")
	@SendTo("/msg/public")
	public MessageWithReacts sendMsgWithFile(MessageSend msg) {
		return serviceWs.sendMessage(msg);
	}

	@MessageMapping("chat.edit")
	@SendTo("/msg/public")
	public MessageWithReacts editMsgWithFile(MessageEdit msg) {
		return serviceWs.editMessage(msg);
	}

	@MessageMapping("chat.delete")
	@SendTo("/msg/public")
	public MessageDelete sendMsgWithFile(String msgId) {
		return serviceWs.deleteMessage(msgId);
	}
}
