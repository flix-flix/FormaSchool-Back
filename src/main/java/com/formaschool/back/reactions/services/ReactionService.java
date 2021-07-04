package com.formaschool.back.reactions.services;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.reactions.Reaction;
import com.formaschool.back.reactions.dto.ReactionModif;

public interface ReactionService extends CRUDService<Reaction> {

	public MessageWithReacts updateReaction(ReactionModif modif);
}
