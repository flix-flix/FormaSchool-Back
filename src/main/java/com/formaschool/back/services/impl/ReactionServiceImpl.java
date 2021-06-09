package com.formaschool.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.Reaction;
import com.formaschool.back.repositories.ReactionRepository;
import com.formaschool.back.services.ReactionService;

public class ReactionServiceImpl extends CRUDServiceImpl<Reaction> implements ReactionService {

	ReactionRepository repo;

	public ReactionServiceImpl(ReactionRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}
}
