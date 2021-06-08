package com.formaschool.back.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


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
}
