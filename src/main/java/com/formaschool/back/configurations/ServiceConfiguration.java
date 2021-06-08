package com.formaschool.back.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public TeamService teamService() {
		return new TeamServiceImpl();
	}

	@Bean
	public SalonService salonService() {
		return new SalonServiceImpl();
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
