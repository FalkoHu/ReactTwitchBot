package com.bot.commands;

import java.io.BufferedWriter;

import com.bot.util.BotCommonHelper;

public interface Command {
	void execute(String[] messageToken, BufferedWriter bw);
}
