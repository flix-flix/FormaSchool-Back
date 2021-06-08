package com.formaschool.back.services;


import java.util.List;

import com.formaschool.back.dto.log.LogWithoutId;
import com.formaschool.back.models.Log;

public interface LogService extends CRUDService<Log>{
	
	/**
	 * This function allows you to find all logs without their ids
	 * @return a list of LogWithoutId object
	 */
	public List<LogWithoutId> findAllWithoutId();
	
	/**
	 * This function allows you to find all logs without their ids by teamId
	 * @param teamId the teamId you re looking for
	 * @return a list of LogWithoutId
	 */
	public List<LogWithoutId> findWithoutIdByTeam(String teamId);

}
