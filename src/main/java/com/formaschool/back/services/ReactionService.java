package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.ReactionUsers;
import com.formaschool.back.models.Reaction;

public interface ReactionService extends CRUDService<Reaction> {
	public List<ReactionUsers> getReactionsUsersOfMessage(String msgId);
}
