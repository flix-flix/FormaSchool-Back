package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.salon.SalonName;
import com.formaschool.back.dto.salon.SalonNameDesc;
import com.formaschool.back.dto.salon.SalonNameDescUpdate;
import com.formaschool.back.models.Salon;

public interface SalonService extends CRUDService<Salon> {
	public SalonNameDesc findById(String id);

	public SalonNameDesc updateSalonNameDesc(SalonNameDescUpdate dto);

	public List<SalonName> findAllSalonNameOfTeam(String teamId);
	
	public List<Salon> findAllSalonOfTeam(String teamId);
}
