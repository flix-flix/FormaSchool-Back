package com.formaschool.back.services.impl;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.salon.SalonNameDescDTO;
import com.formaschool.back.models.Salon;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.services.SalonService;

public class SalonServiceImpl extends CRUDServiceImpl<Salon> implements SalonService {
	
    private ObjectMapper mapper;
    private SalonRepository repo;
    
    public SalonServiceImpl(SalonRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}
	

	@Override
	public SalonNameDescDTO findById(String id) {
		Salon salon = this.repo.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		return mapper.convertValue(salon, SalonNameDescDTO.class);

	}

	
}
