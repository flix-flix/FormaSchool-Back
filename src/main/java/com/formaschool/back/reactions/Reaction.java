package com.formaschool.back.reactions;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formaschool.back.emojis.Emoji;
import com.formaschool.back.members.Member;
import com.formaschool.back.messages.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Reaction {

	@Id
	private String id;

	@DBRef
	private Message message;
	@DBRef
	private Emoji emoji;
	@DBRef
	private Member member;

	public Reaction(Message msg, Member member, Emoji emoji) {
		this.message = msg;
		this.emoji = emoji;
		this.member = member;
	}
}
