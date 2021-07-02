package com.formaschool.back.files;

import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileModel {

	@Id
	private String id;

	/** Filename from user */
	private String name;

	public FileModel(String name) {
		this.name = name;
	}

	/** Returns the filename on the server, format: id.ext (ex: abcdef12345.png) */
	@Transient
	public String getPath() {
		String ext = getExt();
		return id + (ext == null ? "" : ("." + ext));
	}

	/** Returns the filename on the server, format: id.ext (ex: abcdef12345.png) */
	@Transient
	public String getPathWithFolder() {
		// Get the folder from the name, used to split the emojis in folders (ugly af)
		String folder = "";
		int index = name.lastIndexOf("/");
		if (index != -1)
			folder = name.substring(0, index) + "/";

		String ext = getExt();
		return folder + id + (ext == null ? "" : ("." + ext));
	}

	/**
	 * Returns the extension of the original filename or {@code null} if it hasn't
	 * one.
	 */
	@BsonIgnore
	public String getExt() {
		String[] split = name.split("\\.");
		if (split.length == 1)
			return null;

		String ext = split[split.length - 1];
		return ext.length() == 0 ? null : ext;
	}
}
