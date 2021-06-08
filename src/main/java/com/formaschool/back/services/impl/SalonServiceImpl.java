package com.formaschool.back.services.impl;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.salon.SalonNameDescDTO;
import com.formaschool.back.dto.salon.UpdateSalonNameDescDTO;
import com.formaschool.back.models.Salon;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.services.SalonService;



public class SalonServiceImpl extends CRUDServiceImpl<Salon> implements SalonService {
	

    private SalonRepository repo;
    
    public SalonServiceImpl(SalonRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo=repo;
	}
	

	@Override
	public SalonNameDescDTO findById(String id) {
		Salon salon = this.repo.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		return this.mapper.convertValue(salon, SalonNameDescDTO.class);

	}


	@Override
	public SalonNameDescDTO updateSalonNameDesc(UpdateSalonNameDescDTO dto) {
		Salon salon= this.repo.findById(dto.getId())
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(dto.getName()!=null)
			salon.setName(dto.getName());
		if(dto.getDesc() != null)
			salon.setDesc(dto.getDesc());
		Salon result = this.repo.save(salon);
		return this.mapper.convertValue(result, SalonNameDescDTO.class);
	}
}

	
