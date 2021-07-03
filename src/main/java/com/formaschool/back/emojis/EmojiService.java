package com.formaschool.back.emojis;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.emojis.dto.EmojiNamePict;
import com.formaschool.back.emojis.dto.EmojiNamePictUserTeamId;

public interface EmojiService extends CRUDService<Emoji> {

	// ====================================================================================================
	// Management

	public List<EmojiNamePictUserTeamId> findCreatedEmojiByTeamId(String teamId);

	public List<EmojiNamePictUserTeamId> findAllCreatedEmojiOrga();

	public Boolean IsNameAlreadyUse(String id, String name);

	public EmojiNamePictUserTeamId updateEmoji(EmojiNamePictUserTeamId emoji, String idAddedBy);

	public EmojiNamePictUserTeamId addCreatedEmoji(EmojiNamePictUserTeamId emoji, String idAddedBy);

	public void deleteEmoji(String emojiId, String idAddedBy);

	// ====================================================================================================
	// List

	/** Returns the json description of all the default emojis */
	public String getEmojiJSON();

	public List<EmojiNamePict> findAllEmojiOrga();

	public List<EmojiNamePict> findAllEmojiTeam(String teamId);
}
