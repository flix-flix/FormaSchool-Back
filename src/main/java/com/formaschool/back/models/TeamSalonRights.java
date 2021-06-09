package com.formaschool.back.models;


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

	private Boolean managePermissions;
	private Boolean updateSalon;
	private Boolean deleteMsg;
	private Boolean tagSomeone;
	private Boolean seeSalon;
	private Boolean sendMsg;
	/** Allow to add reaction under a msg*/
	private Boolean addReaction;
	
}
