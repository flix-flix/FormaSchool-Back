package com.formaschool.back.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.repositories.TeamRepository;
import com.formaschool.back.services.EmojiService;
import com.formaschool.back.services.MemberService;
import com.formaschool.back.services.MessageService;
import com.formaschool.back.services.SalonService;
import com.formaschool.back.services.TeamService;
import com.formaschool.back.services.UserService;
import com.formaschool.back.services.impl.EmojiServiceImpl;
import com.formaschool.back.services.impl.MemberServiceImpl;
import com.formaschool.back.services.impl.MessageServiceImpl;
import com.formaschool.back.services.impl.SalonServiceImpl;
import com.formaschool.back.services.impl.TeamServiceImpl;
import com.formaschool.back.services.impl.UserServiceImpl;

@Configuration
public class ServiceConfiguration {

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public TeamService teamService(TeamRepository repository , ObjectMapper mapper) {
		return new TeamServiceImpl(repository, mapper);
	}
    
    @Bean
	public SalonService salonService(SalonRepository repository , ObjectMapper mapper) {
		return new SalonServiceImpl(repository, mapper);
	}

	@Bean
	public MessageService msgService() {
		return new MessageServiceImpl();
	}

	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl();
	}

	@Bean
	public EmojiService emojiService() {
		return new EmojiServiceImpl();
	}
}
