package com.formaschool.back.emojis;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back._utils.Utils;
import com.formaschool.back.emojis.dto.EmojiNamePict;
import com.formaschool.back.emojis.dto.EmojiNamePictUserTeamId;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.teams.services.TeamService;
import com.formaschool.back.users.services.UserService;

public class EmojiServiceImpl extends CRUDServiceImpl<Emoji> implements EmojiService {

	private EmojiRepository repo;
	private final Logger LOGGER;

	private TeamService teamService;
	private UserService userService;
	private LogService logService;

	private String json;

	public EmojiServiceImpl(EmojiRepository repo, Utils utils, TeamService teamService, UserService userService,
			LogService logService) {
		super(repo, utils);
		this.repo = repo;
		LOGGER = utils.getLogger("EmojiService");
		this.userService = userService;
		this.teamService = teamService;
		this.logService = logService;

		json = Utils.read("src/main/resources/static/emojis.json");
	}

	// ====================================================================================================
	// Management

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

	// ====================================================================================================
	// List

	@Override
	public String getEmojiJSON() {
		return json;
	}

	@Override
	public List<EmojiNamePict> findAllEmojiOrga() {
		return this.repo.findByUserNotNullAndTeamNull().stream().map(emoji -> dto(emoji, EmojiNamePict.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<EmojiNamePict> findAllEmojiTeam(String teamId) {
		return this.repo.findByUserNotNullAndTeamId(teamId).stream().map(emoji -> dto(emoji, EmojiNamePict.class))
				.collect(Collectors.toList());
	}
}
