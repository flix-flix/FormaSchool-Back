package com.formaschool.back.logs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.logs.dto.LogWithoutId;

@RestController
@RequestMapping("logs")
@CrossOrigin
public class LogController implements CRUDController<Log> {

	@Autowired
	private LogService service;

	@Override
	public CRUDService<Log> getGenericService() {
		return service;
	}

	@GetMapping("withoutId/adminLogs")
	public List<LogWithoutId> findAdminLogsWithoutId() {
		return this.service.findAdminLogsWithoutId();
	}

	@GetMapping("withoutId/{teamId}")
	public List<LogWithoutId> findWithoutIdByTeamId(@PathVariable String teamId) {
		return this.service.findWithoutIdByTeam(teamId);
	}
}
