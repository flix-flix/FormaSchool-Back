package com.formaschool.back.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.formaschool.back.dto.member.MemberUserPseudo;

import lombok.Data;

@Data
public class MessageWithReacts {
	private String id;
	private List<ReactionUsers> reactions;
	private MemberUserPseudo sender;
	private String content;
	private String file;
	private LocalDateTime send;
	private LocalDateTime edit;
}
