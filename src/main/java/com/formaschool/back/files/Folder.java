package com.formaschool.back.files;

import java.io.File;

public enum Folder {

	USERS("users"), TEAMS("teams"), EMOJIS_DEFAULT("emojis"), EMOJIS_ORGA("emojisOrga"), EMOJIS_TEAMS("emojisTeams"),
	SHARED_FILES("sharedFiles");

	private String name;

	private Folder(String name) {
		this.name = name;
	}

	public File getFile(String file) {
		return new File(new File("src/main/resources/static/_uploads/" + name + "/" + file).getAbsolutePath());
	}

	public static Folder fromString(String str) {
		try {
			return valueOf(str.toUpperCase());
		} catch (IllegalArgumentException e) {
		}

		for (Folder folder : values())
			if (folder.name.equals(str))
				return folder;
		return null;
	}
}
