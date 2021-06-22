package com.formaschool.back.logging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogMessageServiceImpl implements LogMessageService {

	@Autowired
	private LogMessageRepository repo;

	@Override
	public List<LogMessage> findAll() {
		return repo.findAll();
	}
}
