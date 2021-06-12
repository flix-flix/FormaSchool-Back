package com.formaschool.back.dto.log;

import java.util.Date;

import com.formaschool.back.dto.user.UserNamePict;

import lombok.Data;

@Data
public class LogWithoutId {
	
	private UserNamePict user;
	private Integer type;
	private Date date;
	private String desc;
}
