package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formaschool.back.dto.messages.MessageDelete;
import com.formaschool.back.dto.messages.MessageSend;
import com.formaschool.back.dto.messages.MessageSendString;
import com.formaschool.back.dto.messages.MessageWithReacts;
import com.formaschool.back.models.Message;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.MessageService;

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

	@PostMapping("sendMsg")
	public MessageWithReacts restMsg(@RequestParam(value = "memberId") String memberId,
			@RequestParam(value = "salonId") String salonId, @RequestParam(value = "content") String content) {
		return service.restSendMsg(new MessageSend(memberId, salonId, content, null));
	}

	@PostMapping("sendMsgWithFile")
	public MessageWithReacts restMsgWithFile(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "memberId") String memberId, @RequestParam(value = "salonId") String salonId,
			@RequestParam(value = "content") String content) {
		return service.restSendMsg(new MessageSend(memberId, salonId, content, file));
	}

	@DeleteMapping("deleteMsg/{msgId}")
	public void restdeleteMsg(@PathVariable String msgId) {
		service.restDeleteMsg(msgId);
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
