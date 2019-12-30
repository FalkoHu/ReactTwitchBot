package com.bot.commands;

import java.io.BufferedWriter;

import com.bot.util.BotCommonHelper;

public class KickCommand implements Command {
	private String question;
	private String[] options;
	
	public void execute(String[] messageToken, BufferedWriter bw) {
		BotCommonHelper.sendMessage(bw, "Received Message");
	}
}
