package com.formaschool.back.services.impl;

import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.MessageWithReacts;
import com.formaschool.back.dto.ReactionUsers;
import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.dto.member.MemberUserPseudo;
import com.formaschool.back.models.Message;
import com.formaschool.back.repositories.MessageRepository;
import com.formaschool.back.repositories.ReactionRepository;
import com.formaschool.back.services.MessageService;

public class MessageServiceImpl extends CRUDServiceImpl<Message> implements MessageService {

	private MessageRepository repo;
	private ReactionRepository reactRepo;

	public MessageServiceImpl(MessageRepository repo, ObjectMapper mapper, ReactionRepository reactRepo) {
		super(repo, mapper);
		this.repo = repo;
		this.reactRepo = reactRepo;
	}

	@Override
	public MessageWithReacts getMessageWithReaction(String id) {
		MessageWithReacts dto = map(repo.findById(id), MessageWithReacts.class);

		// TODO [Move]
		dto.setReactions(reactRepo.findAllByMessageId(id).stream()
				.collect(Collectors.groupingBy(react -> react.getEmoji().getId())).values().stream()
				.map(list -> new ReactionUsers(dto(list.get(0).getEmoji(), EmojiNamePict.class), list.stream()
						.map(react -> dto(react.getMember(), MemberUserPseudo.class)).collect(Collectors.toList())))
				.collect(Collectors.toList()));

		return dto;
	}
}
