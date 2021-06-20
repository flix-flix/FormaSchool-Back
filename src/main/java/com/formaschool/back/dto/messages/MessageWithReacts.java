package com.formaschool.back.dto.messages;

import java.time.LocalDateTime;
import java.util.List;

import com.formaschool.back.dto.ReactionUsers;
import com.formaschool.back.dto.member.MemberUserPseudo;
import com.formaschool.back.models.FileModel;

import lombok.Data;

@Data
public class MessageWithReacts {
	private String id;
	private MemberUserPseudo sender;
	private String salonId;

	private String content;
	private FileModel file;

	private List<ReactionUsers> reactions;

	private LocalDateTime send;
	private LocalDateTime edit;
}