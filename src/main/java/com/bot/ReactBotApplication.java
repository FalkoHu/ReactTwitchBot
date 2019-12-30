package com.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.bot.service.BotService;
import com.bot.ui.ConnectWindow;

@EnableConfigurationProperties
@SpringBootApplication
@ComponentScan
public class ReactBotApplication implements CommandLineRunner {
	
	private final BotService botService;
	
	@Autowired
	public ReactBotApplication(BotService botService) {
		this.botService = botService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ReactBotApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		this.botService.loadCommands();
		this.botService.init();
	}
}
