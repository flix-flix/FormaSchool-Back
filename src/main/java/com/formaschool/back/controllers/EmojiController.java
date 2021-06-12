package com.formaschool.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back.dto.emoji.EmojiNamePict;
import com.formaschool.back.dto.emoji.EmojiNamePictUserTeamId;
import com.formaschool.back.models.Emoji;
import com.formaschool.back.services.CRUDService;
import com.formaschool.back.services.EmojiService;

@CrossOrigin
@RestController
@RequestMapping("emojis")
public class EmojiController implements CRUDController<Emoji>{

	@Autowired
	private EmojiService service;
	
	@Override
	public CRUDService<Emoji> getGenericService() {
		return service;
	}
	
	@GetMapping("createdEmojis/{teamId}")
	public List<EmojiNamePictUserTeamId> findCreatedEmojiByTeamId(@PathVariable String teamId){
		return this.service.findCreatedEmojiByTeamId(teamId);
	}
	
	@GetMapping("createdEmojisOrga")
	public List<EmojiNamePictUserTeamId> findCreatedEmojiOrga(){
		return this.service.findAllCreatedEmojiOrga();
	}
	
	@GetMapping("emojisOrga")
	public List<EmojiNamePict> findAllEmojiOrga(){
		return this.service.findAllEmojiOrga();
	}
	@PatchMapping("createdEmojis")
	public EmojiNamePictUserTeamId updateEmoji(@RequestBody EmojiNamePictUserTeamId emoji) {
		return this.service.updateEmoji(emoji);
	}
	
	@PostMapping("createdEmojis")
	public EmojiNamePictUserTeamId addCreatedEmoji(@RequestBody EmojiNamePictUserTeamId emoji) {
		return this.service.addCreatedEmoji(emoji);
	}
	
	@GetMapping("nameAlreadyUse/{id}/{name}")
	public Boolean IsNameAlreadyUse(@PathVariable String id, @PathVariable String name){
		return this.service.IsNameAlreadyUse(id, name);
	}

}
