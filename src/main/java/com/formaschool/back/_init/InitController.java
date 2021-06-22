package com.formaschool.back._init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("init")
public class InitController {

	@Autowired
	private InitService service;

	@GetMapping("drop")
	public void drop() {
		service.drop();
	}

	@GetMapping()
	public void init() {
		service.init();
	}
}
