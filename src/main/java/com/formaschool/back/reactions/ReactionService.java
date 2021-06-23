package com.formaschool.back.reactions;

import java.util.List;

import com.formaschool.back._crud.CRUDService;

public interface ReactionService extends CRUDService<Reaction> {
	public List<ReactionUsers> getAllReactionsUsersOfMessage(String msgId);
}
