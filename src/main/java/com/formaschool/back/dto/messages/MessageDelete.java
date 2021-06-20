package com.formaschool.back.dto.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDelete {
	private String salonId;
	private String messageId;
}
