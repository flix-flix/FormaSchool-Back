package com.formaschool.back.salons;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.salons.dto.SalonName;
import com.formaschool.back.salons.dto.SalonNameDesc;
import com.formaschool.back.salons.dto.SalonNameDescUpdate;

@RestController
@RequestMapping("salons")
@CrossOrigin
public class SalonController implements CRUDController<Salon> {
	@Autowired
	private SalonService service;

	@Override
	public CRUDService<Salon> getGenericService() {
		return service;
	}

	@GetMapping("salonDesc/{teamId}")
	public SalonNameDesc findById(@PathVariable String teamId) {
		return this.service.findById(teamId);
	}

	@GetMapping("ofTeam/{teamId}")
	public List<SalonName> findAllSalonOfTeam(@PathVariable String teamId) {
		return service.findAllSalonNameOfTeam(teamId);
	}

	@PatchMapping("salonDesc")
	public SalonNameDesc updateSalonNameDesc(@RequestHeader("Authorization") String authorization,
			@RequestBody SalonNameDescUpdate dto) {
		String userId = authorization.split(" ")[1];
		return this.service.updateSalonNameDesc(dto, userId);
	}
}
