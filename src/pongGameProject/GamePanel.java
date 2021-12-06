package pongGameProject;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
	
	// Variables
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.55555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	paddle paddle1;
	paddle paddle2;
	ball ball;
	score score;
	
	GamePanel() {
		
		newPaddles();
		newBall();
		// FIXME never went over the parameters that are in score
		score = new score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	// adds a new ball
	public void newBall() {
		
		random = new Random();
		ball = new ball(((GAME_WIDTH/2) - (BALL_DIAMETER/2)), random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
		
	}
	
	// adds new paddles
	public void newPaddles() {
		
		paddle1 = new paddle(0,(GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new paddle(GAME_WIDTH - PADDLE_WIDTH,(GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
		
	}
	
	// paints
	public void paint(Graphics g) {
		
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
		
	}
	
	// draws
	public void draw (Graphics g) {
		
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		
	}
	
	// moves object
	public void move() {
		
		paddle1.move();
		paddle2.move();
		ball.move();
		
	}
	
	// checks to see if a collision was made
	public void checkCollision() {
		
		// bounce the ball off window edges
		
		if (ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		// bounces ball off paddles
		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional
			if (ball.yVelocity > 0) {
				ball.yVelocity++; // optional
			}
			else {
				ball.yVelocity--;
			}
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		if (ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; // increase velocity after each hit
			if (ball.yVelocity > 0) {
				ball.yVelocity++; // ^^^^^
			}
			else {
				ball.yVelocity--;
			}
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		// stops padds at window edges
		
		if (paddle1.y <= 0) {
			paddle1.y = 0;
		}
		if (paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) {
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		}
		
		if (paddle2.y <= 0) {
			paddle2.y = 0;
		}
		if (paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) {
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		}
		
		// give player point
		if (ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
			System.out.println("PLAYER 2 " + score.player2);
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			System.out.println("PLAYER 1 " + score.player1);
		}
		
	}
	
	// runs the program
	public void run() {
		// game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000.0 / amountOfTicks;
		double delta = 0;
		while (true) { // while running
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
		
	}
	
	// responds to key presses
	// FIXME keyReleased should go in keyReleased???
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			
			paddle1.keyPressed(e);
			paddle1.keyReleased(e);
			paddle2.keyPressed(e);
			paddle2.keyReleased(e);
		
		}
		
		public void keyReleased(KeyEvent e) {
			
		}
	}
}
