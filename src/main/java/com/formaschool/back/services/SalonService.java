package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.salon.SalonName;
import com.formaschool.back.dto.salon.SalonNameDescDTO;
import com.formaschool.back.dto.salon.UpdateSalonNameDescDTO;
import com.formaschool.back.models.Salon;

public interface SalonService extends CRUDService<Salon> {
	public SalonNameDescDTO findById(String id);

	public SalonNameDescDTO updateSalonNameDesc(UpdateSalonNameDescDTO dto);

	public List<SalonName> findAllSalonOfTeam(String teamId);
}
