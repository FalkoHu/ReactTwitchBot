package com.bot.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.bot.Message;
import com.bot.commands.Command;
import com.bot.commands.VoteCommand;
import com.bot.config.BotProperties;
import com.bot.ui.ConnectWindow;
import com.bot.ui.MainWindow;

public class BotServiceImpl implements BotService {
	
	private BotProperties botProperties;
	private MainWindow mainWindow;
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Message message;
	private boolean isRunning;
	
	@Autowired
	public BotServiceImpl(BotProperties botProperties, MainWindow mainWindow) {
		this.botProperties = botProperties;
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void loadCommands() {
		String line, command;
		Resource resource = new ClassPathResource("static/commands.txt");
		
		try (InputStream in = resource.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			while((line = br.readLine()) != null) {
				command = line.substring(0, 1).toUpperCase() + line.substring(1);
				command += "Command";
				try {
					Class<?> clazz = Class.forName("com.bot.commands." + command);
					botProperties.getCommandList().put(line, (Command)clazz.newInstance());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	@Override
	public void init() {
    		ConnectWindow connectWindow = new ConnectWindow(mainWindow, this);
    		connectWindow.createAndShowGUI();
	}
	
	public void connect() {
		try {
			socket = new Socket(botProperties.getHost(), Integer.parseInt(botProperties.getPort()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			message = new Message(botProperties.getUsername(), botProperties.getPass(), botProperties.getCommandList());
			message.sendMessage(writer, "PASS " + botProperties.getPass());
			message.sendMessage(writer, "NICK " + botProperties.getUsername());
			message.sendMessage(writer, "USER " + botProperties.getUsername());
			message.sendMessage(writer, "JOIN #" + botProperties.getChannel());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		Thread t1 = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	loop();
		    }
		});  
		t1.start();
	}
	
	public void loop() {
	       isRunning = true;
	       String line = "";
	       String[] token = null;
	       try {
	    	   while((line = reader.readLine()) != null && isRunning) {
	    		   token = line.split(" ");
	    		   if(token.length > 3 && token[3].substring(1).startsWith("!")) {
	    			   token[0] = token[0].replaceAll(":(.*?)\\!|@(.*)", "");
		    		   if(token[3].toLowerCase().contains(":!vote") && token.length > 3 && token[0] != "titan10x") {
		    			   message.receiveMessage(writer, token[0], token);
		    		   } else {
		    			   message.sendMessage(writer, "Usage: !vote <question> <choice1> <choice2> ... <choice8>");
		    		   }
	    		   }
	    		   mainWindow.setChatArea(line);
	    		   System.out.println(line);
	    	   }
	       } catch (IOException e) {
	    	   e.printStackTrace();
	       }
	}
}
