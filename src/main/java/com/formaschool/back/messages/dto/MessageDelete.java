package com.formaschool.back.messages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDelete {
	private String salonId;
	private String messageId;
}
