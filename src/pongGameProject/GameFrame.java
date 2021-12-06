package pongGameProject;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame{
	
	GamePanel panel;

	GameFrame() {
		
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong Game");		// title
		this.setResizable(false);		// cannot resize
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();		// fits the JFrame around the panel
		this.setVisible(true);	// be able to see the program
		// puts window in the middle of the screen
		this.setLocationRelativeTo(null);
		
	}
	
}
