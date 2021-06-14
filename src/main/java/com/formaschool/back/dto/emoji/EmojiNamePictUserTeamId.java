package com.formaschool.back.dto.emoji;

import com.formaschool.back.dto.user.UserNamePict;

import lombok.Data;

@Data
public class EmojiNamePictUserTeamId {
	private String id;
	private String name;
	private String picture;
	private String teamId;
	private UserNamePict user;
}
