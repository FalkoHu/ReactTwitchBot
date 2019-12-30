package com.bot.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.service.BotService;

import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class MainWindow {
	private JTextArea chatArea;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void createAndShowGUI() {
        JFrame mainFrame = new JFrame("Twitch Bot!");
        mainFrame.setTitle("React Bot for Twitch!");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800,500));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        mainPanel.setLayout(new GridBagLayout());  
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc_textPane = new GridBagConstraints();
        gbc_textPane.insets = new Insets(0, 0, 5, 0);
        gbc_textPane.gridwidth = 7;
        gbc_textPane.gridheight = 7;
        gbc_textPane.fill = GridBagConstraints.BOTH;
        gbc_textPane.gridx = 1;
        gbc_textPane.gridy = 0;
        gbc_textPane.weighty = 1.0;
        gbc_textPane.weightx = 1.0;       
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
        scrollPane.setViewportView(chatArea);
        mainPanel.add(scrollPane, gbc_textPane);
        
        JTextArea userListArea = new JTextArea();
        userListArea.setEditable(false);
        userListArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
        GridBagConstraints gbc_textPane_1 = new GridBagConstraints();
        gbc_textPane_1.gridheight = 7;
        gbc_textPane_1.insets = new Insets(0, 0, 5, 5);
        gbc_textPane_1.fill = GridBagConstraints.BOTH;
        gbc_textPane_1.gridx = 0;
        gbc_textPane_1.gridy = 0;
        mainPanel.add(userListArea, gbc_textPane_1);
        
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        
        GridBagConstraints gbc_btnSend = new GridBagConstraints();
        gbc_btnSend.insets = new Insets(0, 0, 0, 5);
        gbc_btnSend.gridx = 0;
        gbc_btnSend.gridy = 7;
        mainPanel.add(btnSend, gbc_btnSend);
        
        JTextArea textArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.gridwidth = 7;
        textArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 1;
        gbc_textArea.gridy = 7;
        
        mainFrame.getContentPane().add(mainPanel);
        
        mainPanel.add(textArea, gbc_textArea);
        mainFrame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (mainFrame.getWidth()/2), 
                                      middle.y - (mainFrame.getHeight()/2));
        mainFrame.setLocation(newLocation);
        mainFrame.setVisible(true);
	}
	
	public void setChatArea(String message) {
		chatArea.append(message + "\n");
	}
}
