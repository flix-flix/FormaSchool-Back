package com.formaschool.back.reactions.services;

import java.util.List;

import com.formaschool.back.reactions.dto.ReactionUsers;

public interface ReactionBackService {

	public List<ReactionUsers> getAllReactionsUsersOfMessage(String msgId);
}
