package com.formaschool.back.emojis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.emojis.dto.EmojiNamePict;
import com.formaschool.back.emojis.dto.EmojiNamePictUserTeamId;

@CrossOrigin
@RestController
@RequestMapping("emojis")
public class EmojiController implements CRUDController<Emoji> {

	@Autowired
	private EmojiService service;

	@Override
	public CRUDService<Emoji> getGenericService() {
		return service;
	}

	@GetMapping("createdEmojis/{teamId}")
	public List<EmojiNamePictUserTeamId> findCreatedEmojiByTeamId(@PathVariable String teamId) {
		return this.service.findCreatedEmojiByTeamId(teamId);
	}

	@GetMapping("createdEmojisOrga")
	public List<EmojiNamePictUserTeamId> findCreatedEmojiOrga() {
		return this.service.findAllCreatedEmojiOrga();
	}

	@GetMapping("emojisOrga")
	public List<EmojiNamePict> findAllEmojiOrga() {
		return this.service.findAllEmojiOrga();
	}

	@PatchMapping("createdEmojis")
	public EmojiNamePictUserTeamId updateEmoji(@RequestHeader("Authorization") String authorization,
			@RequestBody EmojiNamePictUserTeamId emoji) {
		String userId = authorization.split(" ")[1];
		return this.service.updateEmoji(emoji, userId);
	}

	@PostMapping("createdEmojis")
	public EmojiNamePictUserTeamId addCreatedEmoji(@RequestHeader("Authorization") String authorization, 
			@RequestBody EmojiNamePictUserTeamId emoji) {
		String userId = authorization.split(" ")[1];
		return this.service.addCreatedEmoji(emoji, userId);
	}

	@GetMapping("nameAlreadyUse/{id}/{name}")
	public Boolean IsNameAlreadyUse(@PathVariable String id, @PathVariable String name) {
		return this.service.IsNameAlreadyUse(id, name);
	}
}
