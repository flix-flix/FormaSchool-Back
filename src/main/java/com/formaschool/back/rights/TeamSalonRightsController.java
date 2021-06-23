package com.formaschool.back.rights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;

@CrossOrigin
@RestController
@RequestMapping("teamSalonRights")
public class TeamSalonRightsController implements CRUDController<TeamSalonRights> {

	@Autowired
	private TeamSalonRightsService service;

	@Override
	public CRUDService<TeamSalonRights> getGenericService() {
		return service;
	}
}
