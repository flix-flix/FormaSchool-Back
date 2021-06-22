package com.formaschool.back.logging;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "log-back")
public class LogMessage {

	@Id
	private String id;

	@Field(type = FieldType.Date, format = DateFormat.basic_date_time)
	private ZonedDateTime date;
	private String level;
	private String who;
	private String message;

	public LogMessage(String level, String who, String message) {
		this.level = level;
		this.who = who;
		this.message = message;
		this.date = ZonedDateTime.now();
	}
}
