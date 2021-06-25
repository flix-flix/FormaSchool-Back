package com.formaschool.back._utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	@Autowired
	private static ObjectMapper mapper;

	/** Throw BAD_REQUEST if the Optional is empty */
	public static <T> T opt(Optional<T> opt) {
		return opt.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
	}

	/** Map the entity into the DTO */
	public static <E, DTO> DTO dto(E entity, Class<DTO> cl) {
		return mapper.convertValue(entity, cl);
	}

	/** Map the entity into the DTO */
	public static <T, DTO> DTO dtoOpt(Optional<T> opt, Class<DTO> cl) {
		return dto(opt(opt), cl);
	}
}
