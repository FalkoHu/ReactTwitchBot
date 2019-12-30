package com.bot.util;

import com.bot.commands.Command;
import com.bot.config.BotProperties;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class BotCommonHelper {
	private static Map<String, Command> commandList = BotProperties.getCommandList();
    
    public static void receiveMessage(BufferedWriter bw, String userName, String[] messageToken) {
    	String command = messageToken[3];
    	if(command.substring(1).startsWith("!")) {
    		if(commandList.containsKey(command.substring(2))) {
    			commandList.get(command.substring(2)).execute(messageToken, bw);
    		}
    	}
    }

    public static void sendMessage(BufferedWriter bw, String message) {
    	try {
    		bw.write(message + "\r\n");
    		bw.flush();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}
