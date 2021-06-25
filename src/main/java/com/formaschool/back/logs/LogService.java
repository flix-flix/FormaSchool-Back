package com.formaschool.back.logs;


import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.emojis.Emoji;
import com.formaschool.back.logs.dto.LogWithoutId;
import com.formaschool.back.salons.Salon;
import com.formaschool.back.teams.Team;

public interface LogService extends CRUDService<Log>{
	
	/**
	 * This function allows you to find all logs without their ids
	 * @return a list of LogWithoutId object
	 */
	public List<LogWithoutId> findAdminLogsWithoutId();
	
	/**
	 * This function allows you to find all logs without their ids by teamId
	 * @param teamId the teamId you re looking for
	 * @return a list of LogWithoutId
	 */
	public List<LogWithoutId> findWithoutIdByTeam(String teamId);
	
	public void updateSalonLog(Salon salon, String idAddedBy);
	
	public void addUserLog(String firstname, String lastname, String idAddedBy);
	
	public void addTeamLog(Team team, String idAddedBy);
	
	public void updateTeamLog(Team team, String idAddedBy);
	
	public void addEmojiLog(Emoji emoji, String idAddedBy);
	
	public void updateEmojiLog(Emoji emoji, String oldname , String idAddedBy);
	
	public void deleteEmojiLog(Emoji emoji, String idAddedBy);

}
