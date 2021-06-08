package com.formaschool.back.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.repositories.EmojiRepository;
import com.formaschool.back.repositories.LogRepository;
import com.formaschool.back.repositories.MemberRepository;
import com.formaschool.back.repositories.MessageRepository;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.repositories.TeamRepository;
import com.formaschool.back.repositories.UserRepository;
import com.formaschool.back.services.EmojiService;
import com.formaschool.back.services.LogService;
import com.formaschool.back.services.MemberService;
import com.formaschool.back.services.MessageService;
import com.formaschool.back.services.SalonService;
import com.formaschool.back.services.TeamService;
import com.formaschool.back.services.UserService;
import com.formaschool.back.services.impl.EmojiServiceImpl;
import com.formaschool.back.services.impl.LogServiceImpl;
import com.formaschool.back.services.impl.MemberServiceImpl;
import com.formaschool.back.services.impl.MessageServiceImpl;
import com.formaschool.back.services.impl.SalonServiceImpl;
import com.formaschool.back.services.impl.TeamServiceImpl;
import com.formaschool.back.services.impl.UserServiceImpl;

@Configuration
public class ServiceConfiguration {
	

	@Bean
	public UserService userService(UserRepository repo, ObjectMapper mapper) {
		return new UserServiceImpl(repo, mapper);
	}

	@Bean
	public TeamService teamService(TeamRepository repo , ObjectMapper mapper) {
		return new TeamServiceImpl(repo, mapper);
	}
    
	@Bean
	public SalonService salonService(SalonRepository repo, ObjectMapper mapper) {
		return new SalonServiceImpl(repo, mapper);

	}

	@Bean
	public MessageService msgService(MessageRepository repo, ObjectMapper mapper) {
		return new MessageServiceImpl(repo, mapper);
	}

	@Bean
	public MemberService memberService(MemberRepository repo, ObjectMapper mapper) {
		return new MemberServiceImpl(repo, mapper);
	}

	@Bean
	public EmojiService emojiService(EmojiRepository repo, ObjectMapper mapper) {
		return new EmojiServiceImpl(repo, mapper);
	}

	@Bean
	public LogService logService(LogRepository repository, ObjectMapper mapper) {
		return new LogServiceImpl(repository, mapper);
	}
}
