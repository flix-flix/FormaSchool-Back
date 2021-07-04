package com.formaschool.back.reactions.impl;

import com.formaschool.back._crud.CRUDServiceImpl;
import com.formaschool.back._utils.Utils;
import com.formaschool.back.emojis.Emoji;
import com.formaschool.back.members.Member;
import com.formaschool.back.messages.Message;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.messages.services.MessageService;
import com.formaschool.back.reactions.Reaction;
import com.formaschool.back.reactions.ReactionRepository;
import com.formaschool.back.reactions.dto.ReactionModif;
import com.formaschool.back.reactions.services.ReactionService;

public class ReactionServiceImpl extends CRUDServiceImpl<Reaction> implements ReactionService {

	private ReactionRepository repo;

	private MessageService message;

	public ReactionServiceImpl(ReactionRepository repo, Utils utils, MessageService message) {
		super(repo, utils);
		this.repo = repo;
		this.message = message;
	}

	// ====================================================================================================
	// WebSocket

	@Override
	public MessageWithReacts updateReaction(ReactionModif modif) {
		if (modif.isOn()) {
			if (repo.findByMessageIdAndMemberIdAndEmojiId(modif.getMsgId(), modif.getMemberId(), modif.getEmojiId())
					.isEmpty())
				repo.save(new Reaction(new Message(modif.getMsgId()), new Member(modif.getMemberId()),
						new Emoji(modif.getEmojiId())));
		} else
			repo.deleteByMessageIdAndMemberIdAndEmojiId(modif.getMsgId(), modif.getMemberId(), modif.getEmojiId());
		return message.getMessageWithReacts(modif.getMsgId());
	}
}
