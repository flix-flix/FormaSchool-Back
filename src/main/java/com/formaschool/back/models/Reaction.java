package com.formaschool.back.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private Message msg;
	@DBRef
	private Emoji emoji;
	@DBRef
	private Member member;

	public Reaction(Message msg, Member member, Emoji emoji) {
		this.msg = msg;
		this.emoji = emoji;
		this.member = member;
	}
}
