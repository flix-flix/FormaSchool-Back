package com.formaschool.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.models.Message;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.MessageService;

@RestController
@RequestMapping("messages")
public class MessageController implements CRUDController<Message> {
	@Autowired
	private MessageService service;

	@Override
	public CRUDService<Message> getGenericService() {
		return service;
	}
}
