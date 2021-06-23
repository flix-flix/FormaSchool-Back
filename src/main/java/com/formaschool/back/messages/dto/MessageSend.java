package com.formaschool.back.messages.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageSend {

	private String memberId;
	private String salonId;

	private String content;
	private MultipartFile file;
}
