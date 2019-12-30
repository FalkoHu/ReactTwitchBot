package com.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bot.ui.MainWindow;

@Configuration
@ComponentScan
public class BotConfig {

//	@Bean
//	public BotService botService(BotProperties botProperties, MainWindow mainWindow) {
//		return new BotServiceImpl(botProperties, mainWindow);
//	}
//
	@Bean MainWindow mainWindow() {
		return new MainWindow();
	}

}
