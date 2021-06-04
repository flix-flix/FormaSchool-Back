package com.formaschool.back.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class TeamSalonRights {
	
	@Id
	private String id;

	private Boolean deleteMsg;
	private Boolean tagSomeone;
	private Boolean seeMsg;
	private Boolean sendMsg;
	private Boolean addEmoji;
}
