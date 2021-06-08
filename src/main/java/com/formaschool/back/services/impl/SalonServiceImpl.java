package com.formaschool.back.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.SalonNameDescDTO;
import com.formaschool.back.models.Salon;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.services.SalonService;

public class SalonServiceImpl extends CRUDServiceImpl<Salon> implements SalonService {
	
    private ObjectMapper mapper;
	private SalonRepository repository;
	
	public SalonServiceImpl(SalonRepository repository, ObjectMapper mapperParam) {
		this.repository = repository;
		this.mapper = mapperParam;
	}

	@Override
	public SalonNameDescDTO findById(String id) {
		Salon salon = this.repository.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		return mapper.convertValue(salon, SalonNameDescDTO.class);
	}
}
