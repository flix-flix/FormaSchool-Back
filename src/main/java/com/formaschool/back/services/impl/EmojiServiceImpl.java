package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.models.Emoji;
import com.formaschool.back.repositories.EmojiRepository;
import com.formaschool.back.services.EmojiService;

public class EmojiServiceImpl extends CRUDServiceImpl<Emoji> implements EmojiService {

	private EmojiRepository repo;

	public EmojiServiceImpl(EmojiRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}
	
	public List<Emoji> findCreatedEmojiByTeamId(String teamId){
		return this.repo.findByUserNotNullAndTeamId(teamId);
	}

	@Override
	public List<Emoji> findAllCreatedEmojiOrga() {
		return this.repo.findByUserNotNullAndTeamNull();
	}

	@Override
	public List<EmojiNamePict> findAllEmojiOrga() {
		List<Emoji> emojis = this.repo.findByUserNullAndTeamNull();
		return emojis.stream().map(emoji -> {
			return this.mapper.convertValue(emoji, EmojiNamePict.class);
		}).collect(Collectors.toList());
	}
}
