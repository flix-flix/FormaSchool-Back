package com.formaschool.back.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Message {

	@Id
	private String id;

	@DBRef
	private Member sender;
	@DBRef
	private Salon salon;
	@NonNull
	private String content;
	private String file;
	private LocalDateTime send = LocalDateTime.now();
	private LocalDateTime edit;
	private List<Reaction> reaction;

	public Message(Member sender, Salon salon, @NonNull String content, String file, LocalDateTime send,
			LocalDateTime edit) {
		this.sender = sender;
		this.salon = salon;
		this.content = content;
		this.file = file;
		this.send = send;
		this.edit = edit;
	}

	public Message(Member sender, Salon salon, @NonNull String content, String file, LocalDateTime send,
			LocalDateTime edit, List<Reaction> reactions) {
		this(sender, salon, content, file, send, edit);
		this.reaction = reactions;
	}
}
