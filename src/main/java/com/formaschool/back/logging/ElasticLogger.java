package com.formaschool.back.logging;

import org.springframework.beans.factory.annotation.Autowired;

public class ElasticLogger extends Logger {

	@Autowired
	private LogMessageRepository repo;

	public ElasticLogger(String who, LogMessageRepository repo) {
		super(who);
		this.repo = repo;
	}

	@Override
	protected void log(String level, String who, String message) {
		repo.save(new LogMessage(level, who, message));
	}
}
