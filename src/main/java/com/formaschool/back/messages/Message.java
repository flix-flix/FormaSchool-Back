package com.formaschool.back.messages;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formaschool.back.files.FileModel;
import com.formaschool.back.members.Member;
import com.formaschool.back.salons.Salon;

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
	@DBRef
	private FileModel file;
	private LocalDateTime send = LocalDateTime.now();
	private LocalDateTime edit;

	public Message(Member sender, Salon salon, @NonNull String content, FileModel file, LocalDateTime send,
			LocalDateTime edit) {
		this.sender = sender;
		this.salon = salon;
		this.content = content;
		this.file = file;
		this.send = send;
		this.edit = edit;
	}

	public Message(String id) {
		this.id = id;
	}
}
