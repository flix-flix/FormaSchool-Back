package com.formaschool.back.dto;

import java.util.List;

import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.dto.user.UserName;

import lombok.Data;

@Data
public class ReactionUsers {
	private EmojiNamePict emoji;
	private List<UserName> users;
}
