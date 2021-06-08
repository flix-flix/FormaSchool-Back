package com.formaschool.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.models.Log;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.LogService;

@RestController
@RequestMapping("logs")
public class LogController implements CRUDController<Log> {
	
	@Autowired
	private LogService service;

	@Override
	public CRUDService<Log> getGenericService() {
		return service;
	}

}
