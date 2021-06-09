package com.formaschool.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.models.Reaction;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.ReactionService;

@RestController
@RequestMapping("reactions")
@CrossOrigin
public class ReactionController implements CRUDController<Reaction> {
	@Autowired
	private ReactionService service;

	@Override
	public CRUDService<Reaction> getGenericService() {
		return service;
	}
}
