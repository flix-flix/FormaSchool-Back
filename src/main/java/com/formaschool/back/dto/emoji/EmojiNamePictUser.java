package com.formaschool.back.dto.emoji;

import com.formaschool.back.dto.user.UserNamePict;

import lombok.Data;

@Data
public class EmojiNamePictUser {
	
	private String name;
	private String picture;
	private UserNamePict user;

}
