package com.formaschool.back.dto.log;

import java.time.LocalDateTime;

import com.formaschool.back.models.Team;
import com.formaschool.back.models.User;

import lombok.Data;

@Data
public class LogWithoutId {
	
	private User user;
	private Team team;
	private Integer type;
	private LocalDateTime date;
	private String desc;
}
