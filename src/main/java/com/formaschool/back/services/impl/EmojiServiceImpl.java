package com.formaschool.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.Emoji;
import com.formaschool.back.repositories.EmojiRepository;
import com.formaschool.back.services.EmojiService;

public class EmojiServiceImpl extends CRUDServiceImpl<Emoji> implements EmojiService {

	private EmojiRepository repo;

	public EmojiServiceImpl(EmojiRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}
}
