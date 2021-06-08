package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.formaschool.back.dto.log.LogWithoutId;
import com.formaschool.back.models.Log;
import com.formaschool.back.repositories.LogRepository;
import com.formaschool.back.services.LogService;

public class LogServiceImpl  extends CRUDServiceImpl<Log> implements LogService{
	
	@Autowired
	private LogRepository repository;
	private ObjectMapper mapper;
   
	public LogServiceImpl(LogRepository repository, ObjectMapper mapper) {
		this.repository = repository;
		mapper.findAndRegisterModules();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        this.mapper = mapper;
	}
	
	public List<LogWithoutId> findAllWithoutId(){
		List<Log> logs = this.repository.findAll();
		return logs.stream().map(log ->{
			return this.mapper.convertValue(log, LogWithoutId.class);
		}).collect(Collectors.toList());
	}
	
	public List<LogWithoutId> findWithoutIdByTeam(String teamId){
		List<Log> logs = this.repository.findByTeamId(teamId);
		return logs.stream().map(log ->{
			return this.mapper.convertValue(log, LogWithoutId.class);
		}).collect(Collectors.toList());
	}
}
