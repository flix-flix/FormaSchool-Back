package com.formaschool.back.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.users.dto.settings.UserPassword;
import com.formaschool.back.users.dto.settings.UserSettings;
import com.formaschool.back.users.services.UserSettingsService;

@RestController
@RequestMapping("userSettings")
@CrossOrigin
public class UserSettingsController {

	@Autowired
	private UserSettingsService service;

	@GetMapping("{id}")
	public UserSettings getSettings(@PathVariable String id) {
		return service.getSettingsById(id);
	}

	@PatchMapping()
	public UserSettings updateSettings(@RequestBody UserSettings dto) {
		System.out.println(dto);
		return this.service.updateSettings(dto);
	}

	@PatchMapping("password")
	public UserSettings updatePassword(@RequestBody UserPassword dto) {
		System.out.println(dto);
		// TODO
		return null;
	}
}
