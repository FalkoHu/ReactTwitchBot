package com.bot.service;

import org.springframework.stereotype.Service;

@Service
public interface BotService {
	 public void init();
	 public void loadCommands();
	 public void loop();
	 public void connect();
}