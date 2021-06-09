package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.log.LogWithoutId;
import com.formaschool.back.models.Log;
import com.formaschool.back.repositories.LogRepository;
import com.formaschool.back.services.LogService;

public class LogServiceImpl extends CRUDServiceImpl<Log> implements LogService {

	private LogRepository repository;

	public LogServiceImpl(LogRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<LogWithoutId> findAllWithoutId() {
		List<Log> logs = this.repository.findAll();
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
