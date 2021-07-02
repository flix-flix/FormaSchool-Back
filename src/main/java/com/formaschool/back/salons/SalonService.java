package com.formaschool.back.salons;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.salons.dto.SalonMessages;
import com.formaschool.back.salons.dto.SalonName;
import com.formaschool.back.salons.dto.SalonNameDesc;
import com.formaschool.back.salons.dto.SalonNameDescUpdate;

public interface SalonService extends CRUDService<Salon> {

	// ====================================================================================================
	// Messages

	public SalonMessages getSalonWithMessages(String salonId);

	public List<SalonName> findAllSalonNameOfTeam(String teamId);

	// ====================================================================================================
	// Params

	public SalonNameDesc findById(String id);

	public SalonNameDesc updateSalonNameDesc(SalonNameDescUpdate dto, String idAddedBy);

	// ====================================================================================================

	public List<Salon> findAllSalonOfTeam(String teamId);
}
