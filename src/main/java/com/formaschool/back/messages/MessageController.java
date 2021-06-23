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
import com.formaschool.back.messages.dto.MessageSendString;
import com.formaschool.back.messages.dto.MessageWithReacts;

@RestController
@RequestMapping("messages")
@CrossOrigin
public class MessageController implements CRUDController<Message> {
	@Autowired
	private MessageService service;

	@Override
	public CRUDService<Message> getGenericService() {
		return service;
	}

	// ====================================================================================================

	@GetMapping("withReacts/{msgId}")
	public MessageWithReacts getMessageWithReactions(@PathVariable String msgId) {
		return service.getMessageWithReacts(msgId);
	}

	@GetMapping("salonWithReacts/{salonId}")
	public List<MessageWithReacts> getAllMessageWithReactionsOfSalon(@PathVariable String salonId) {
		return service.getAllMessageWithReactsOfSalon(salonId);
	}

	// ====================================================================================================

	@MessageMapping("chat.send")
	@SendTo("/topic/public")
	public MessageWithReacts sendMsgWithFile(MessageSendString msg) {
		return service.sendMessage(msg);
	}

	@MessageMapping("chat.delete")
	@SendTo("/topic/public")
	public MessageDelete sendMsgWithFile(String msgId) {
		return service.deleteMessage(msgId);
	}
}