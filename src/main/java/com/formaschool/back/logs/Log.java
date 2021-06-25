package com.formaschool.back.logs;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formaschool.back.teams.Team;
import com.formaschool.back.users.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Log {
	
	@Id
	private String id;
	
	@DBRef
	private User user;
	
	@DBRef
	private Team team;
	
	private Integer type;
	private LocalDateTime date;
	private String desc;
	
	public Log(User user, Team team, Integer type, LocalDateTime date, String desc) {
		super();
		this.user = user;
		this.team = team;
		this.type = type;
		this.date = date;
		this.desc = desc;
	}
}
