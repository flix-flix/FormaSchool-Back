package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.dto.emoji.EmojiNamePictUserTeamId;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.logging.LoggerFactory;
import com.formaschool.back.models.Emoji;
import com.formaschool.back.repositories.EmojiRepository;
import com.formaschool.back.services.EmojiService;
import com.formaschool.back.services.TeamService;
import com.formaschool.back.services.UserService;

public class EmojiServiceImpl extends CRUDServiceImpl<Emoji> implements EmojiService {

	private EmojiRepository repo;
	private final Logger LOGGER;

	private TeamService teamService;
	private UserService userService;

	public EmojiServiceImpl(EmojiRepository repo, ObjectMapper mapper, LoggerFactory factory, TeamService teamService,
			UserService userService) {
		super(repo, mapper);
		this.repo = repo;
		LOGGER = factory.getElasticLogger("EmojiService");
		this.userService = userService;
		this.teamService = teamService;
	}

	@Override
	public List<EmojiNamePictUserTeamId> findCreatedEmojiByTeamId(String teamId) {
		List<Emoji> emojis = this.repo.findByUserNotNullAndTeamId(teamId);
		return emojis.stream().map(emoji -> {
			return this.mapper.convertValue(emoji, EmojiNamePictUserTeamId.class);
		}).collect(Collectors.toList());
	}

	@Override
	public List<EmojiNamePictUserTeamId> findAllCreatedEmojiOrga() {
		List<Emoji> emojis = this.repo.findByUserNotNullAndTeamNull();
		return emojis.stream().map(emoji -> {
			return this.mapper.convertValue(emoji, EmojiNamePictUserTeamId.class);
		}).collect(Collectors.toList());
	}

	@Override
	public List<EmojiNamePict> findAllEmojiOrga() {
		List<Emoji> emojis = this.repo.findByUserNullAndTeamNull();
		return emojis.stream().map(emoji -> {
			return this.mapper.convertValue(emoji, EmojiNamePict.class);
		}).collect(Collectors.toList());
	}

	@Override
	public Boolean IsNameAlreadyUse(String id, String name) {
		return this.repo.findByIdNotAndName(id, name).size() != 0;
	}

	@Override
	public EmojiNamePictUserTeamId updateEmoji(EmojiNamePictUserTeamId emoji) {
		Emoji result = this.repo.findById(emoji.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
		result.setName(emoji.getName());

		// TODO
		// result.setPicture(emoji.getPicture());
		this.repo.save(result);
		return dto(result, EmojiNamePictUserTeamId.class);
	}

	@Override
	public EmojiNamePictUserTeamId addCreatedEmoji(EmojiNamePictUserTeamId emoji) {
		Emoji result = new Emoji();
		result.setName(emoji.getName());
		// TODO
		// result.setPicture(emoji.getPicture());
		if (emoji.getTeamId() != null) {
			result.setTeam(this.teamService.get(emoji.getTeamId()));
		}
		result.setUser(this.userService.get(emoji.getUser().getId()));
		this.repo.save(result);
		LOGGER.info("Create emoji: " + result);
		return dto(result, EmojiNamePictUserTeamId.class);
	}
}
