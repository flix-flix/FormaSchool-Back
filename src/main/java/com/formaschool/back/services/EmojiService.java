package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.models.Emoji;

public interface EmojiService extends CRUDService<Emoji> {
	
	public List<Emoji> findCreatedEmojiByTeamId(String teamId);
	
	public List<Emoji> findAllCreatedEmojiOrga();
	
	public List<EmojiNamePict> findAllEmojiOrga();
}

