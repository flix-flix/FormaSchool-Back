package com.formaschool.back.rights;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamSalonRights {
	

	private Boolean managePermissions;
	private Boolean updateSalon;
	private Boolean deleteMsg;
	private Boolean tagSomeone;
	private Boolean seeSalon;
	private Boolean sendMsg;
	/** Allow to add reaction under a msg*/
	private Boolean addReaction;
	
}
