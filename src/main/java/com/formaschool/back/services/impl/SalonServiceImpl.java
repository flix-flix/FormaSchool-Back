package com.formaschool.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.Salon;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.services.SalonService;

public class SalonServiceImpl extends CRUDServiceImpl<Salon> implements SalonService {

	private SalonRepository repo;

	public SalonServiceImpl(SalonRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}
}
