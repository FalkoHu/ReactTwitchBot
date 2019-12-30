package com.bot.service;

import com.bot.util.BotCommonHelper;
import com.bot.commands.Command;
import com.bot.config.BotProperties;
import com.bot.ui.ConnectWindow;
import com.bot.ui.MainWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static com.bot.util.BotConstants.COMMAND_CLASS_PATH;
import static com.bot.util.BotConstants.REGEX_LOOP;

public class BotServiceImpl implements BotService {

    private BotProperties botProperties;
    private MainWindow mainWindow;
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private BotCommonHelper botCommonHelper;
    private boolean isRunning;

    @Autowired
    public BotServiceImpl(BotProperties botProperties, MainWindow mainWindow) {
        this.botProperties = botProperties;
        this.mainWindow = mainWindow;
    }

    @Override
    public void loadCommands() {
        String line = "";
        StringBuilder command = new StringBuilder();
        Resource resource = new ClassPathResource(COMMAND_CLASS_PATH);
        Map<String, Command> commandMap = new HashMap<>();

        try (InputStream in = resource.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            while ((line = br.readLine()) != null) {
                command.append(line.substring(0, 1).toUpperCase() + line.substring(1));
                command.append("Command");
                try {
                    Class<?> clazz = Class.forName("com.bot.commands." + command);
                    commandMap.put(line, (Command) clazz.newInstance());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            botProperties.setCommandList(commandMap);
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
            BotCommonHelper.sendMessage(writer, "PASS " + botProperties.getPass());
            botCommonHelper.sendMessage(writer, "NICK " + botProperties.getUsername());
            botCommonHelper.sendMessage(writer, "USER " + botProperties.getUsername());
            botCommonHelper.sendMessage(writer, "JOIN #" + botProperties.getChannel());
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
            while ((line = reader.readLine()) != null && isRunning) {
                token = line.split(" ");
                if (token.length > 3 && token[3].substring(1).startsWith("!")) {
                    token[0] = token[0].replaceAll(REGEX_LOOP, "");
                    if (token[3].toLowerCase().contains(":!vote") && token.length > 3 && token[0] != "titan10x") {
                        botCommonHelper.receiveMessage(writer, token[0], token);
                    } else {
                        botCommonHelper.sendMessage(writer, "Usage: !vote <question> <choice1> <choice2> ... <choice8>");
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
