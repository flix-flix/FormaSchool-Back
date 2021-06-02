package com.formaschool.back.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Message {

	@Id
	private String id;
	@NonNull
	private String content;
	private String file;

	private LocalDate send = LocalDate.now();
	private LocalDate edit;
}
