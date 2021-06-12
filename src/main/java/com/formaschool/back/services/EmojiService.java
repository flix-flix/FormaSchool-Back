package com.formaschool.back.services;

import java.util.List;


import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.dto.emoji.EmojiNamePictUserTeamId;
import com.formaschool.back.models.Emoji;

public interface EmojiService extends CRUDService<Emoji> {
	
	public List<EmojiNamePictUserTeamId> findCreatedEmojiByTeamId(String teamId);
	
	public List<EmojiNamePictUserTeamId> findAllCreatedEmojiOrga();
	
	public List<EmojiNamePict> findAllEmojiOrga();
	
	public Boolean IsNameAlreadyUse(String id, String name);
	
	public EmojiNamePictUserTeamId updateEmoji(EmojiNamePictUserTeamId emoji);
	
	public EmojiNamePictUserTeamId addCreatedEmoji(EmojiNamePictUserTeamId emoji);
}

