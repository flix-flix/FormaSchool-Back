package com.formaschool.back.logging;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogMessageRepository extends ElasticsearchRepository<LogMessage, String> {

	@Override
	public List<LogMessage> findAll();

	public List<LogMessage> findAllByLevel(String level);
}
