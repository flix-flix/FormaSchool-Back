package com.formaschool.back.emojis.dto;

import com.formaschool.back.users.dto.UserNamePict;

import lombok.Data;

@Data
public class EmojiNamePictUserTeamId {
	private String id;
	private String name;
	private String picture;
	private String teamId;
	private UserNamePict user;
}
