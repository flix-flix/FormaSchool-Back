package com.formaschool.back.dto.log;

import java.time.LocalDateTime;

import com.formaschool.back.dto.user.UserNamePict;

import lombok.Data;

@Data
public class LogWithoutId {
	
	private UserNamePict user;
	private Integer type;
	private LocalDateTime date;
	private String desc;
}
