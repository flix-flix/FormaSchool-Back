package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.ReactionUsers;
import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.dto.member.MemberUserPseudo;
import com.formaschool.back.models.Reaction;
import com.formaschool.back.repositories.ReactionRepository;
import com.formaschool.back.services.ReactionService;

public class ReactionServiceImpl extends CRUDServiceImpl<Reaction> implements ReactionService {

	private ReactionRepository repo;

	public ReactionServiceImpl(ReactionRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}

	@Override
	public List<ReactionUsers> getReactionsUsersOfMessage(String msgId) {
		return repo.findAllByMessageId(msgId).stream().collect(Collectors.groupingBy(react -> react.getEmoji().getId()))
				.values().stream()
				.map(list -> new ReactionUsers(dto(list.get(0).getEmoji(), EmojiNamePict.class), list.stream()
						.map(react -> dto(react.getMember(), MemberUserPseudo.class)).collect(Collectors.toList())))
				.collect(Collectors.toList());
	}
}
