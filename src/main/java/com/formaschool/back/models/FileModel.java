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
public class FileModel {

	@Id
	private String id;

	/** Filename from user */
	private String name;

	public FileModel(String name) {
		this.name = name;
	}

	/** Returns the filename on the server, format: id.ext (ex: abcdef12345.png) */
	public String getPath() {
		String ext = getExt();
		return id + (ext == null ? "" : ("." + ext));
	}

	/**
	 * Returns the extension of the original filename or {@code null} if it hasn't
	 * one.
	 */
	public String getExt() {
		String[] split = name.split("\\.");
		if (split.length == 1)
			return null;

		String ext = split[split.length - 1];
		return ext.length() == 0 ? null : ext;
	}
}
