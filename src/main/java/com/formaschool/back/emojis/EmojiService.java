package com.formaschool.back.emojis;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.emojis.dto.EmojiNamePict;
import com.formaschool.back.emojis.dto.EmojiNamePictUserTeamId;

public interface EmojiService extends CRUDService<Emoji> {
	
	public List<EmojiNamePictUserTeamId> findCreatedEmojiByTeamId(String teamId);
	
	public List<EmojiNamePictUserTeamId> findAllCreatedEmojiOrga();
	
	public List<EmojiNamePict> findAllEmojiOrga();
	
	public Boolean IsNameAlreadyUse(String id, String name);
	
	public EmojiNamePictUserTeamId updateEmoji(EmojiNamePictUserTeamId emoji, String idAddedBy);
	
	public EmojiNamePictUserTeamId addCreatedEmoji(EmojiNamePictUserTeamId emoji, String idAddedBy);
}

