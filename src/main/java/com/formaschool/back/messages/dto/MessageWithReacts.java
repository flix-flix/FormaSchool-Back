package com.formaschool.back.messages.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.formaschool.back.files.FileModel;
import com.formaschool.back.members.dto.MemberUserPseudo;
import com.formaschool.back.reactions.dto.ReactionUsers;

import lombok.Data;

@Data
public class MessageWithReacts {
	private String id;
	private MemberUserPseudo sender;
	private String teamId;
	private String salonId;

	private String content;
	private FileModel file;

	private List<ReactionUsers> reactions;

	private LocalDateTime send;
	private LocalDateTime edit;
}
