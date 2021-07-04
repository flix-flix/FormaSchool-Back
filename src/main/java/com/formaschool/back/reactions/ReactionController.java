package com.formaschool.back.reactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.messages.dto.MessageWithReacts;
import com.formaschool.back.reactions.dto.ReactionModif;
import com.formaschool.back.reactions.services.ReactionService;

@RestController
@RequestMapping("reactions")
@CrossOrigin
public class ReactionController implements CRUDController<Reaction> {

	@Autowired
	private ReactionService service;

	@Override
	public CRUDService<Reaction> getGenericService() {
		return service;
	}

	// ====================================================================================================
	// WebSocket

	@MessageMapping("chat.react")
	@SendTo("/msg/public")
	public MessageWithReacts changeReaction(ReactionModif modif) {
		return service.updateReaction(modif);
	}
}
