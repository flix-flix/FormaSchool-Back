package com.formaschool.back.dto;

import java.util.List;

import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.dto.member.MemberUserPseudo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReactionUsers {
	private EmojiNamePict emoji;
	private List<MemberUserPseudo> members;
}
