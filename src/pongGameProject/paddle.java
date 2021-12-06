package pongGameProject;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class paddle extends Rectangle{
	
	int id;
	int yVelocity;
	// FIXME adjust speed as needed
	int speed = 30;
	
	paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id = id;
	}
	
	public void keyPressed(KeyEvent e) {
		
		switch(id) {
			case 1:
				if (e.getKeyCode()==KeyEvent.VK_W) { // player1 hits the w key
					setYDirection(-speed);
					move();
				}
				if (e.getKeyCode()==KeyEvent.VK_S) { // player1 hits the s key
					setYDirection(speed);
					move();
				}
				break;
			case 2:
				if (e.getKeyCode()==KeyEvent.VK_UP) { // player2 hits the up arrow
					setYDirection(-speed);
					move();
				}
				if (e.getKeyCode()==KeyEvent.VK_DOWN) { // player2 hits the down arrow
					setYDirection(speed);
					move();
				}
				break;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if (e.getKeyCode()==KeyEvent.VK_W) { // player1 releases the w key
				setYDirection(0);
				move();
			}
			if (e.getKeyCode()==KeyEvent.VK_S) { // player1 releases the s key
				setYDirection(0);
				move();
			}
			break;
		case 2:
			if (e.getKeyCode()==KeyEvent.VK_UP) { // player2 releases the up arrow
				setYDirection(0);
				move();
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) { // player2 releases the down arrow
				setYDirection(0);
				move();
			}
			break;
	}
		
	}
	
	public void setYDirection(int yDirection) {
		
		yVelocity = yDirection;
		
	}
	
	public void move() {
		
		y = y + yVelocity;
		
	}
	
	public void draw(Graphics g) {
		if (id == 1) {
			g.setColor(Color.blue);
		}
		else {
			g.setColor(Color.red);
		}
		g.fillRect(x, y, width, height);
	}

}
