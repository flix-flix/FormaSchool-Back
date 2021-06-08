package com.formaschool.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.SalonNameDescDTO;
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
	@GetMapping("salonDesc/{id}")
	public SalonNameDescDTO findById(@PathVariable String id) {
		return this.service.findById(id);
	}
}
