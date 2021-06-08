package com.formaschool.back.services.impl;

<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.SalonNameDescDTO;
=======
import com.fasterxml.jackson.databind.ObjectMapper;
>>>>>>> 4d3799398e0fc55ec717658cebd04ffb0bd0e9ea
import com.formaschool.back.models.Salon;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.services.SalonService;

public class SalonServiceImpl extends CRUDServiceImpl<Salon> implements SalonService {
	
    private ObjectMapper mapper;
    private SalonRepository repo;
	
	public SalonServiceImpl(SalonRepository repository, ObjectMapper mapperParam) {
		this.repo = repository;
		this.mapper = mapperParam;
	}

	@Override
	public SalonNameDescDTO findById(String id) {
		Salon salon = this.repo.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		return mapper.convertValue(salon, SalonNameDescDTO.class);

	

	public SalonServiceImpl(SalonRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}
}
