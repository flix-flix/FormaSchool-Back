package com.formaschool.back.reactions;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReactionRepository extends MongoRepository<Reaction, String> {

	public Optional<Reaction> findByMessageIdAndMemberIdAndEmojiId(String msgId, String memberId, String emojiId);

	public void deleteByMessageIdAndMemberIdAndEmojiId(String msgId, String memberId, String emojiId);

	public List<Reaction> findAllByMessageId(String msgId);
}
