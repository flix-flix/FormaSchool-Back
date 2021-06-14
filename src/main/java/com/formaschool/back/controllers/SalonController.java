package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.salon.SalonName;
import com.formaschool.back.dto.salon.SalonNameDesc;
import com.formaschool.back.dto.salon.SalonNameDescUpdate;
import com.formaschool.back.models.Salon;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.SalonService;

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

	@PatchMapping("salonDesc/{id}")
	public SalonNameDesc updateSalonNameDesc(@RequestBody SalonNameDescUpdate dto) {
		return this.service.updateSalonNameDesc(dto);
	}
}
