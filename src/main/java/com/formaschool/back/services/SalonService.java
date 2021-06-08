package com.formaschool.back.services;

import com.formaschool.back.dto.SalonNameDescDTO;
import com.formaschool.back.models.Salon;


public interface SalonService extends CRUDService<Salon> {
	public SalonNameDescDTO findById(String id);
}
