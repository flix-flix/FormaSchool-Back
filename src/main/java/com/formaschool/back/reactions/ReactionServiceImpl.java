package com.formaschool.back.reactions;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back.emojis.dto.EmojiNamePict;
import com.formaschool.back.members.dto.MemberUserPseudo;

public class ReactionServiceImpl extends CRUDServiceImpl<Reaction> implements ReactionService {

	private ReactionRepository repo;

	public ReactionServiceImpl(ReactionRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}

	@Override
	public List<ReactionUsers> getAllReactionsUsersOfMessage(String msgId) {
		return repo.findAllByMessageId(msgId).stream().collect(Collectors.groupingBy(react -> react.getEmoji().getId()))
				.values().stream()
				.map(list -> new ReactionUsers(dto(list.get(0).getEmoji(), EmojiNamePict.class), list.stream()
						.map(react -> dto(react.getMember(), MemberUserPseudo.class)).collect(Collectors.toList())))
				.collect(Collectors.toList());
	}
}
