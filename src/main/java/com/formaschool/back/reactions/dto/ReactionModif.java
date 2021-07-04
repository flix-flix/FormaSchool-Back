package com.formaschool.back.reactions.dto;

import lombok.Data;

@Data
public class ReactionModif {
	private String msgId;
	private String memberId;
	private String emojiId;
	private boolean on;
}
