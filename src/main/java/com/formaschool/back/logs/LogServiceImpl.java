package com.formaschool.back.logs;

import static com.formaschool.back._utils.Utils.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back.emojis.Emoji;
import com.formaschool.back.logs.dto.LogWithoutId;
import com.formaschool.back.salons.Salon;
import com.formaschool.back.teams.Team;
import com.formaschool.back.users.User;

public class LogServiceImpl extends CRUDServiceImpl<Log> implements LogService {

	private LogRepository repository;

	public LogServiceImpl(LogRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	@Override
	public List<LogWithoutId> findAdminLogsWithoutId() {
		List<Log> logs = this.repository.findByTeamIdNull();
		return logs.stream().map(log -> {
			return dto(log, LogWithoutId.class);
		}).collect(Collectors.toList());
	}

	@Override
	public List<LogWithoutId> findWithoutIdByTeam(String teamId) {
		List<Log> logs = this.repository.findByTeamId(teamId);
		return logs.stream().map(log -> {
			return dto(log, LogWithoutId.class);
		}).collect(Collectors.toList());
	}

	@Override
	public void updateSalonLog(Salon salon, String idAddedBy) {
		String desc = "a modife le salon " + salon.getName();
		Log log = new Log(new User(idAddedBy), salon.getTeam(), Type.UPDATE_SALON.ordinal(), LocalDateTime.now(), desc);
		this.repository.save(log);
	}

	@Override
	public void addUserLog(String firstname, String lastname, String idAddedBy) {
		String desc = "a créer un utilisateur " + firstname + lastname;
		Log log = new Log(new User(idAddedBy), null, Type.CREATE_USER.ordinal(), LocalDateTime.now(), desc);
		this.repository.save(log);
	}

	@Override
	public void addTeamLog(Team team, String idAddedBy) {
		String desc = "a créer la team " + team.getName();
		Log log = new Log(new User(idAddedBy), null, Type.CREATE_TEAM.ordinal(), LocalDateTime.now(), desc);
		this.repository.save(log);
	}

	@Override
	public void updateTeamLog(Team team, String idAddedBy) {
		String desc = "a changé le nom et/ou la description de l'equipe";
		Log log = new Log(new User(idAddedBy), team, Type.UPDATE_TEAM.ordinal(), LocalDateTime.now(), desc);
		this.repository.save(log);
	}

	@Override
	public void addEmojiLog(Emoji emoji, String idAddedBy) {
		String desc = "a créer l'emoji " + emoji.getName();
		Log log = new Log(new User(idAddedBy), emoji.getTeam(), Type.CREATE_EMOJI.ordinal(), LocalDateTime.now(), desc);
		this.repository.save(log);
	}

	@Override
	public void updateEmojiLog(Emoji emoji, String oldname, String idAddedBy) {
		String desc = "a modifie l'emoji " + oldname + " par " + emoji.getName();
		Log log = new Log(new User(idAddedBy), emoji.getTeam(), Type.UPDATE_EMOJI.ordinal(), LocalDateTime.now(), desc);
		this.repository.save(log);
	}

	@Override
	public void deleteEmojiLog(Emoji emoji, String idAddedBy) {
		String desc = "a supprimer l'emoji " + emoji.getName();
		Log log = new Log(new User(idAddedBy), emoji.getTeam(), Type.DELETE_EMOJI.ordinal(), LocalDateTime.now(), desc);
		this.repository.save(log);
	}
}
