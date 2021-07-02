package com.formaschool.back._utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.logging.Logger;
import com.formaschool.back.logging.LoggerFactory;

public class Utils {

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private LoggerFactory factory;

	// ====================================================================================================

	/** Throw BAD_REQUEST if the Optional is empty */
	public <T> T opt(Optional<T> opt) {
		return opt.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id unknown"));
	}

	/** Map the entity into the DTO */
	public <E, DTO> DTO dto(E entity, Class<DTO> cl) {
		return mapper.convertValue(entity, cl);
	}

	// ====================================================================================================

	public Logger getLogger(String name) {
		return factory.getElasticLogger(name);
	}

	// ====================================================================================================

	public static String read(String name) {
		StringWriter sw = new StringWriter();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(name)), "UTF8"));
			char[] arr = new char[1024];
			int size;

			while ((size = br.read(arr)) > 0)
				sw.write(arr, 0, size);

			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
}
