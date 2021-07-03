package com.formaschool.back.messages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageSend {

	private String memberId;
	private String salonId;

	private String content;
	private String file;
	private String fileName;
}
