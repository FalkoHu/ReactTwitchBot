package com.bot.ui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.config.BotProperties;
import com.bot.service.BotService;

public class ConnectWindow {
	private MainWindow mainWindow;
	private BotService botService;
	
	@Autowired
	public ConnectWindow(MainWindow mainWindow, BotService botService) {
		this.mainWindow = mainWindow;
		this.botService = botService;
	}

    public void createAndShowGUI() {
        JFrame connectFrame = new JFrame("Twitch Bot!");
        connectFrame.setTitle("Connect to a server");
        connectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connectFrame.setResizable(false);
        JPanel connectPanel = new JPanel();
        connectPanel.setSize(500,500);
        connectPanel.setLocation(500,400); 
        connectPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        connectPanel.setLayout(new GridBagLayout());  
        
        JLabel lblHeader = new JLabel("Login to Twitch");
        lblHeader.setFont(lblHeader.getFont().deriveFont(Font.BOLD, 11f));
        GridBagConstraints gbc_lblHeader = new GridBagConstraints();
        gbc_lblHeader.gridwidth = 2;
        gbc_lblHeader.insets = new Insets(3, 0, 10, 0);
        gbc_lblHeader.gridx = 0;
        gbc_lblHeader.gridy = 0;
        connectPanel.add(lblHeader, gbc_lblHeader);
        
        JLabel lblHost = new JLabel("Host Name");
        lblHost.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHost.setText("Host:");  
        GridBagConstraints gbc_lblHost = new GridBagConstraints();
        gbc_lblHost.anchor = GridBagConstraints.WEST;
        gbc_lblHost.ipadx = 2;
        gbc_lblHost.insets = new Insets(0, 0, 5, 5);
        gbc_lblHost.gridx = 0;
        gbc_lblHost.gridy = 1;
        connectPanel.add(lblHost, gbc_lblHost); 
        
        final JTextField resultField = new JTextField(20);
        resultField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        GridBagConstraints gbc_resultField = new GridBagConstraints();
        gbc_resultField.insets = new Insets(0, 0, 5, 0);
        gbc_resultField.gridx = 1;
        gbc_resultField.gridy = 1;
        connectPanel.add(resultField, gbc_resultField);   
        
        connectFrame.getContentPane().add(connectPanel);
        
        JLabel lblPort = new JLabel("Port Name");
        lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPort.setText("Port:");  
        GridBagConstraints gbc_lblPort = new GridBagConstraints();
        gbc_lblPort.anchor = GridBagConstraints.WEST;
        gbc_lblPort.insets = new Insets(0, 0, 5, 5);
        gbc_lblPort.ipadx = 2;
        gbc_lblPort.gridx = 0;
        gbc_lblPort.gridy = 2;
        connectPanel.add(lblPort, gbc_lblPort); 
        
        final JTextField resultField2 = new JTextField(20);
        resultField2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        GridBagConstraints gbc_resultField2 = new GridBagConstraints();
        gbc_resultField2.insets = new Insets(0, 0, 5, 0);
        gbc_resultField2.gridx = 1;
        gbc_resultField2.gridy = 2;
        connectPanel.add(resultField2, gbc_resultField2);  
        
        JLabel lblUser = new JLabel("Username");
        lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUser.setText("Username:");  
        GridBagConstraints gbc_lblUser = new GridBagConstraints();
        gbc_lblUser.anchor = GridBagConstraints.WEST;
        gbc_lblUser.insets = new Insets(0, 0, 5, 5);
        gbc_lblUser.ipadx = 2;
        gbc_lblUser.gridx = 0;
        gbc_lblUser.gridy = 3;
        connectPanel.add(lblUser, gbc_lblUser); 
        
        final JTextField resultField3 = new JTextField(20);
        resultField3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        GridBagConstraints gbc_resultField3 = new GridBagConstraints();
        gbc_resultField3.insets = new Insets(0, 0, 5, 0);
        gbc_resultField3.gridx = 1;
        gbc_resultField3.gridy = 3;
        connectPanel.add(resultField3, gbc_resultField3);  
        
        JLabel lblPass = new JLabel("Password");
        lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPass.setText("Password:");  
        GridBagConstraints gbc_lblPass = new GridBagConstraints();
        gbc_lblPass.anchor = GridBagConstraints.WEST;
        gbc_lblPass.insets = new Insets(0, 0, 5, 5);
        gbc_lblPass.ipadx = 2;
        gbc_lblPass.gridx = 0;
        gbc_lblPass.gridy = 4;
        connectPanel.add(lblPass, gbc_lblPass); 
        
        final JTextField resultField4 = new JTextField(20);
        resultField4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        GridBagConstraints gbc_resultField4 = new GridBagConstraints();
        gbc_resultField4.insets = new Insets(0, 0, 5, 0);
        gbc_resultField4.gridx = 1;
        gbc_resultField4.gridy = 4;
        connectPanel.add(resultField4, gbc_resultField4);  
        
        JLabel lblChan = new JLabel("Channel");
        lblChan.setHorizontalAlignment(SwingConstants.RIGHT);
        lblChan.setText("Channel:");  
        GridBagConstraints gbc_lblChan = new GridBagConstraints();
        gbc_lblChan.anchor = GridBagConstraints.WEST;
        gbc_lblChan.insets = new Insets(0, 0, 5, 5);
        gbc_lblChan.ipadx = 2;
        gbc_lblChan.gridx = 0;
        gbc_lblChan.gridy = 5;
        connectPanel.add(lblChan, gbc_lblChan); 
        
        final JTextField resultField5 = new JTextField(20);
        resultField5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        GridBagConstraints gbc_resultField5 = new GridBagConstraints();
        gbc_resultField5.insets = new Insets(0, 0, 5, 0);
        gbc_resultField5.gridx = 1;
        gbc_resultField5.gridy = 5;
        connectPanel.add(resultField5, gbc_resultField5);  
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		connectFrame.dispatchEvent(new WindowEvent(connectFrame, WindowEvent.WINDOW_CLOSING));
        	}
        });
        GridBagConstraints gbc_btnCancel = new GridBagConstraints();
        gbc_btnCancel.anchor = GridBagConstraints.EAST;
        gbc_btnCancel.insets = new Insets(10, 0, 0, 10);
        gbc_btnCancel.gridx = 0;
        gbc_btnCancel.gridy = 6;
        connectPanel.add(btnCancel, gbc_btnCancel);
        
        JButton btnConnect = new JButton("Connect");
        btnConnect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!resultField.getText().equals("") && !resultField2.getText().equals("") && !resultField3.getText().equals("")
        				&& !resultField4.getText().equals("") && !resultField5.getText().equals("")) {
        			connectFrame.setVisible(false);
        			mainWindow.createAndShowGUI();
        		     SwingUtilities.invokeLater(new Runnable() {
        		         public void run() {
        		        	 botService.connect();
        		         }
        		     });
        		}
        	}
        });
        
        GridBagConstraints gbc_btnConnect = new GridBagConstraints();
        gbc_btnConnect.anchor = GridBagConstraints.EAST;
        gbc_btnConnect.gridx = 1;
        gbc_btnConnect.gridy = 6;
        connectPanel.add(btnConnect, gbc_btnConnect);
        connectFrame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (connectFrame.getWidth() - 1), 
                                      middle.y - (connectFrame.getHeight()));
        connectFrame.setLocation(newLocation);
        connectFrame.setVisible(true);
    }
    
    public MainWindow getMainWindow() {
    	return this.mainWindow;
    }
}
