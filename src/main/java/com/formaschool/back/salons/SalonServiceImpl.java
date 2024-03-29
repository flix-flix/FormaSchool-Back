package com.formaschool.back.salons;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back._utils.Utils;
import com.formaschool.back.logs.LogService;
import com.formaschool.back.messages.services.MessageService;
import com.formaschool.back.salons.dto.SalonMessages;
import com.formaschool.back.salons.dto.SalonName;
import com.formaschool.back.salons.dto.SalonNameDesc;
import com.formaschool.back.salons.dto.SalonNameDescUpdate;

public class SalonServiceImpl extends CRUDServiceImpl<Salon> implements SalonService {

	private SalonRepository repo;
	private LogService logService;
	private MessageService messageService;

	public SalonServiceImpl(SalonRepository repo, Utils utils, LogService logService, MessageService messageService) {
		super(repo, utils);
		this.repo = repo;
		this.logService = logService;
		this.messageService = messageService;
	}

	// ====================================================================================================
	// Messages

	@Override
	public SalonMessages getSalonWithMessages(String salonId) {
		Salon entity = opt(repo.findById(salonId));
		SalonMessages dto = dto(entity, SalonMessages.class);
		dto.setMessages(messageService.getAllMessageWithReactsOfSalon(salonId));
		return dto;
	}

	@Override
	public List<SalonName> findAllSalonNameOfTeam(String teamId) {
		return repo.findByTeamId(teamId).stream().map(salon -> dto(salon, SalonName.class))
				.collect(Collectors.toList());
	}

	// ====================================================================================================
	// Params

	@Override
	public SalonNameDesc findById(String id) {
		Salon salon = this.repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return dto(salon, SalonNameDesc.class);
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
		return dto(result, SalonNameDesc.class);
	}

	// ====================================================================================================

	@Override
	public List<Salon> findAllSalonOfTeam(String teamId) {
		return repo.findByTeamId(teamId);
	}
}
