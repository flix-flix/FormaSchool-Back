package com.formaschool.back.logging;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Logger {

	private String who;

	protected abstract void log(String level, String who, String message);

	public void trace(String message) {
		this.log("TRACE", getWho(), message);
	}

	public void debug(String message) {
		this.log("DEBUG", getWho(), message);
	}

	public void info(String message) {
		this.log("INFO", getWho(), message);
	}

	public void warn(String message) {
		this.log("WARN", getWho(), message);
	}

	public void error(String message) {
		this.log("ERROR", getWho(), message);
	}
}
