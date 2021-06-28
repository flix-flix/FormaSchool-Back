package com.formaschool.back.emojis;

import static com.formaschool.back._utils.Utils.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back.emojis.dto.EmojiNamePict;
import com.formaschool.back.emojis.dto.EmojiNamePictUserTeamId;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.logging.LoggerFactory;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.teams.services.TeamService;
import com.formaschool.back.users.UserService;

public class EmojiServiceImpl extends CRUDServiceImpl<Emoji> implements EmojiService {

	private EmojiRepository repo;
	private final Logger LOGGER;

	private TeamService teamService;
	private UserService userService;
	private LogService logService;

	public EmojiServiceImpl(EmojiRepository repo, ObjectMapper mapper, LoggerFactory factory, TeamService teamService,
			UserService userService, LogService logService) {
		super(repo, mapper);
		this.repo = repo;
		LOGGER = factory.getElasticLogger("EmojiService");
		this.userService = userService;
		this.teamService = teamService;
		this.logService = logService;
	}

	@Override
	public List<EmojiNamePictUserTeamId> findCreatedEmojiByTeamId(String teamId) {
		List<Emoji> emojis = this.repo.findByUserNotNullAndTeamId(teamId);
		return emojis.stream().map(emoji -> {
			return dto(emoji, EmojiNamePictUserTeamId.class);
		}).collect(Collectors.toList());
	}

	@Override
	public List<EmojiNamePictUserTeamId> findAllCreatedEmojiOrga() {
		List<Emoji> emojis = this.repo.findByUserNotNullAndTeamNull();
		return emojis.stream().map(emoji -> {
			return dto(emoji, EmojiNamePictUserTeamId.class);
		}).collect(Collectors.toList());
	}

	@Override
	public List<EmojiNamePict> findAllEmojiOrga() {
		List<Emoji> emojis = this.repo.findByUserNullAndTeamNull();
		return emojis.stream().map(emoji -> {
			return dto(emoji, EmojiNamePict.class);
		}).collect(Collectors.toList());
	}

	@Override
	public Boolean IsNameAlreadyUse(String id, String name) {
		return this.repo.findByIdNotAndName(id, name).size() != 0;
	}

	@Override
	public EmojiNamePictUserTeamId updateEmoji(EmojiNamePictUserTeamId emoji, String idAddedBy) {
		Emoji result = this.repo.findById(emoji.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
		String oldname = result.getName();
		result.setName(emoji.getName());
		this.repo.save(result);
		this.logService.updateEmojiLog(result, oldname, idAddedBy);
		return dto(result, EmojiNamePictUserTeamId.class);
	}

	@Override
	public EmojiNamePictUserTeamId addCreatedEmoji(EmojiNamePictUserTeamId emoji, String idAddedBy) {
		Emoji result = new Emoji();
		result.setName(emoji.getName());
		if (emoji.getTeamId() != null) {
			result.setTeam(this.teamService.get(emoji.getTeamId()));
		}
		result.setUser(this.userService.get(emoji.getUser().getId()));
		this.repo.save(result);
		LOGGER.info("Create emoji: " + result);
		this.logService.addEmojiLog(result, idAddedBy);
		return dto(result, EmojiNamePictUserTeamId.class);
	}

	@Override
	public void deleteEmoji(String emojiId, String idAddedBy) {
		Emoji entity = this.repo.findById(emojiId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
		this.logService.deleteEmojiLog(entity, idAddedBy);
		this.repo.deleteById(emojiId);
	}
}
