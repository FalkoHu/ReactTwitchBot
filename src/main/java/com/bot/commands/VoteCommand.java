package com.bot.commands;

import java.io.BufferedWriter;

import com.bot.util.BotCommonHelper;

public class VoteCommand implements Command {
	private String question;
	private String[] options;

	
	public void execute(String[] messageToken, BufferedWriter bw) {
		question = messageToken[1];
		options = messageToken;
		
		BotCommonHelper.sendMessage(bw, "PRIVMSG #" + "titan10x" + " :" + messageToken[0] + " initiated a vote!");
	}
}
