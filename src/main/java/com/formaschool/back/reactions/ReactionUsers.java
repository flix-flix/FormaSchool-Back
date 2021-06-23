package com.formaschool.back.reactions;

import java.util.List;

import com.formaschool.back.emojis.dto.EmojiNamePict;
import com.formaschool.back.members.dto.MemberUserPseudo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReactionUsers {
	private EmojiNamePict emoji;
	private List<MemberUserPseudo> members;
}
