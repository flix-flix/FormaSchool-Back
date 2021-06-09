package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.MessageWithReacts;
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

	@GetMapping("withReacts/{msgId}")
	public MessageWithReacts getMessageWithReactions(@PathVariable String msgId) {
		return service.getMessageWithReacts(msgId);
	}

	@GetMapping("salonWithReacts/{salonId}")
	public List<MessageWithReacts> getAllMessageWithReactionsOfSalon(@PathVariable String salonId) {
		return service.getAllMessageWithReactsOfSalon(salonId);
	}
}
