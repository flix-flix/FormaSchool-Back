package com.formaschool.back.logs.dto;

import java.time.LocalDateTime;

import com.formaschool.back.users.dto.UserNamePict;

import lombok.Data;

@Data
public class LogWithoutId {
	private UserNamePict user;
	private Integer type;
	private LocalDateTime date;
	private String desc;
}
