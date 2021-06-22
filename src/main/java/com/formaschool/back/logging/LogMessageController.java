package com.formaschool.back.logging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logMessages")
@CrossOrigin
public class LogMessageController {

	@Autowired
	private LogMessageService service;

	@GetMapping()
	public List<LogMessage> findAll() {
		return service.findAll();
	}
}
