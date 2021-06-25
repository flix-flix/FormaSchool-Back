package com.formaschool.back.logs;


import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.logs.dto.LogWithoutId;

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
	
	public Log addLog(Log log);

}
