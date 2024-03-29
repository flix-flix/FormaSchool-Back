package com.formaschool.back.configurations.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.formaschool.back._init.InitService;
import com.formaschool.back._utils.Utils;

@Configuration
public class CommonsConfig {
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setVisibility(
				VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		return mapper;
	}

	@Bean
	public InitService init() {
		return new InitService();
	}

	@Bean
	public Utils utils() {
		return new Utils();
	}
}
