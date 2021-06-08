package com.formaschool.back.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Salon {

	@Id
	private String id;
	@NonNull
	private String name;
	private String desc;


	private Integer teamId;

	private Integer[] Message;
}
