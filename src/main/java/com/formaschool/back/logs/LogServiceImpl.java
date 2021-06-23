package com.formaschool.back.logs;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back.logs.dto.LogWithoutId;

public class LogServiceImpl extends CRUDServiceImpl<Log> implements LogService {

	private LogRepository repository;

	public LogServiceImpl(LogRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<LogWithoutId> findAdminLogsWithoutId() {
		List<Log> logs = this.repository.findByTeamIdNull();
		return logs.stream().map(log -> {
			return this.mapper.convertValue(log, LogWithoutId.class);
		}).collect(Collectors.toList());
	}

	public List<LogWithoutId> findWithoutIdByTeam(String teamId) {
		List<Log> logs = this.repository.findByTeamId(teamId);
		return logs.stream().map(log -> {
			return this.mapper.convertValue(log, LogWithoutId.class);
		}).collect(Collectors.toList());
	}
}