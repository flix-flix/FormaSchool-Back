package com.formaschool.back.dto.salon;

import com.formaschool.back.dto.team.TeamNamePict;

import lombok.Data;

@Data
public class SalonName {
	private String id;
	private TeamNamePict team;
	private String name;
}
