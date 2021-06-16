package com.formaschool.back.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.formaschool.back.dto.member.MemberUserPseudo;
import com.formaschool.back.models.File;

import lombok.Data;

@Data
public class MessageWithReacts {
	private String id;
	private List<ReactionUsers> reactions;
	private MemberUserPseudo sender;
	private String content;
	private File file;
	private LocalDateTime send;
	private LocalDateTime edit;
}
