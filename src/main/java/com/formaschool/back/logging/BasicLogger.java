package com.formaschool.back.logging;

public class BasicLogger extends Logger {

	public BasicLogger(String who) {
		super(who);
	}

	@Override
	protected void log(String level, String who, String message) {
		System.out.println(String.format("[%5s] %-25s: %s", level, who, message));
	}
}
