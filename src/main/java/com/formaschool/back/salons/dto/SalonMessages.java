package com.formaschool.back.salons.dto;

import java.util.List;

import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.teams.dto.TeamNamePict;

import lombok.Data;

@Data
public class SalonMessages {
	private String id;
	private TeamNamePict team;
	private String name;

	private List<MessageWithReacts> messages;
}
