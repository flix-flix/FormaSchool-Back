package com.formaschool.back.salons.dto;

import com.formaschool.back.teams.dto.TeamNamePict;

import lombok.Data;

@Data
public class SalonName {
	private String id;
	private TeamNamePict team;
	private String name;
}
