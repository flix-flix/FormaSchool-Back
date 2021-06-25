package com.formaschool.back.salons;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.salons.dto.SalonName;
import com.formaschool.back.salons.dto.SalonNameDesc;
import com.formaschool.back.salons.dto.SalonNameDescUpdate;

public class SalonServiceImpl extends CRUDServiceImpl<Salon> implements SalonService {

	private SalonRepository repo;
	private LogService logService;

	public SalonServiceImpl(SalonRepository repo, ObjectMapper mapper, LogService logService) {
		super(repo, mapper);
		this.repo = repo;
		this.logService = logService;
	}

	@Override
	public SalonNameDesc findById(String id) {
		Salon salon = this.repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return this.mapper.convertValue(salon, SalonNameDesc.class);
	}

	@Override
	public SalonNameDesc updateSalonNameDesc(SalonNameDescUpdate dto, String idAddedBy) {
		Salon salon = this.repo.findById(dto.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (dto.getName() != null)
			salon.setName(dto.getName());
		if (dto.getDesc() != null)
			salon.setDesc(dto.getDesc());
		Salon result = this.repo.save(salon);
		this.logService.updateSalonLog(result, idAddedBy);
		return this.mapper.convertValue(result, SalonNameDesc.class);
	}

	@Override
	public List<SalonName> findAllSalonNameOfTeam(String teamId) {
		return repo.findByTeamId(teamId).stream().map(salon -> dto(salon, SalonName.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Salon> findAllSalonOfTeam(String teamId) {
		return repo.findByTeamId(teamId);
	}
}
