package com.formaschool.back.reactions.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.formaschool.back._utils.Utils;
import com.formaschool.back.emojis.dto.EmojiNamePict;
import com.formaschool.back.members.dto.MemberUserPseudo;
import com.formaschool.back.reactions.ReactionRepository;
import com.formaschool.back.reactions.dto.ReactionUsers;
import com.formaschool.back.reactions.services.ReactionBackService;

public class ReactionBackServiceImpl implements ReactionBackService {

	private ReactionRepository repo;
	private Utils utils;

	public ReactionBackServiceImpl(ReactionRepository repo, Utils utils) {
		this.repo = repo;
		this.utils = utils;
	}

	// ====================================================================================================

	@Override
	public List<ReactionUsers> getAllReactionsUsersOfMessage(String msgId) {
		return repo.findAllByMessageId(msgId).stream().collect(Collectors.groupingBy(react -> react.getEmoji().getId()))
				.values().stream()
				.map(list -> new ReactionUsers(utils.dto(list.get(0).getEmoji(), EmojiNamePict.class),
						list.stream().map(react -> utils.dto(react.getMember(), MemberUserPseudo.class))
								.collect(Collectors.toList())))
				.collect(Collectors.toList());
	}
}
