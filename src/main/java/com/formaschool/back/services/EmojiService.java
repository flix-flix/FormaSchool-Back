package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.dto.emoji.EmojiNamePictUser;
import com.formaschool.back.models.Emoji;

public interface EmojiService extends CRUDService<Emoji> {
	
	public List<EmojiNamePictUser> findCreatedEmojiByTeamId(String teamId);
	
	public List<EmojiNamePictUser> findAllCreatedEmojiOrga();
	
	public List<EmojiNamePict> findAllEmojiOrga();
}

