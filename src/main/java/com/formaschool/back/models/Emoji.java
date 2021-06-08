package com.formaschool.back.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Emoji {

	@Id
	private String id;

	private String name;
	private String picture;

	public Emoji(String name, String picture) {
		this.name = name;
		this.picture = picture;
	}
}
