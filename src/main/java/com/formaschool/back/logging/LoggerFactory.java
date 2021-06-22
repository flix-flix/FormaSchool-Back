package com.formaschool.back.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoggerFactory {

	@Autowired
	private LogMessageRepository repo;

	public static Logger getLogger(String who) {
		return new BasicLogger(who);
	}

	public Logger getElasticLogger(String who) {
		ElasticLogger logger = new ElasticLogger(who, repo);
		logger.trace("Create MessageService");
		return logger;
	}
}
