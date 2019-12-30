package com.bot.commands;

import java.io.BufferedWriter;

import com.bot.Message;

public class VoteCommand implements Command {
	private String question;
	private String[] options;
	
	
	public void execute(String[] messageToken, BufferedWriter bw, Message message) {
		question = messageToken[1];
		options = messageToken;
		
		message.sendMessage(bw, "PRIVMSG #" + "titan10x" + " :" + messageToken[0] + " initiated a vote!");
	}
}
