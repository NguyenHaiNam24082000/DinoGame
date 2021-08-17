package userinterface;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import effect.SoundPlay;
import objects.GameWorld;
import objects.Init;

public class GamePanel extends JPanel implements Runnable, KeyListener, ActionListener, MouseListener {
	private Thread thread;
	private boolean isRunning;
	private BufferedImage backgroundImage;
	private Icon imagePause;
	private Icon imageNext;
	private JButton playAgainButton;
	private JButton homeButton;
	private JButton exitButton;
	private JButton starRoomButton;
	private JButton restartButton;
	private GameWorld gameWorld;
	private JButton pauseButton;
	private boolean isPaused = false;
	private int imageBoardGameOverY = 800;
	private ArrayList<BufferedImage> backgroundBrownImages;
	private int backgroundSpeedY = 2;
	private int backgroundPosY = -320;
	private BufferedImage landImage;
	private boolean isChangeBG=false;
	private int randomIndexBG;
	public GamePanel() {
		super();
		this.setLayout(null);
		gameWorld = new GameWorld();
		this.loadImage();
		this.addButton();
	}

	public void loadImage() {
		backgroundBrownImages = new ArrayList<BufferedImage>();
		int randomIndexBG = randomBackground();
		try {
			for (int i = 0; i < 4; ++i) {
				backgroundBrownImages
						.add(ImageIO.read(new File("data/Background/BackgroundStarRoom-" + randomIndexBG + ".png")));
			}
			landImage = ImageIO.read(new File("data/Land.png"));
			backgroundImage = ImageIO.read(new File("data/Background(432x768).png"));
			imagePause = new ImageIcon("data/pause.png");
			imageNext = new ImageIcon("data/next.png");
		} catch (IOException ex) {
			System.out.println("error data Image in GamePanel");
		}
	}

	public void addButton() {
		// add Pause Button
		pauseButton = new JButton(imagePause);
		pauseButton.setBorder(BorderFactory.createEmptyBorder());
		pauseButton.setBounds(Init.MAX_WIDTH_FRAME - 100, 10, imagePause.getIconWidth(), imagePause.getIconHeight());
		pauseButton.setContentAreaFilled(false);
		pauseButton.setFocusable(false);
		pauseButton.addActionListener(this);
		pauseButton.setVisible(true);
		this.add(pauseButton);
		// add Play Again Button
		playAgainButton = new JButton(new ImageIcon("data/Play.png"));
		playAgainButton.setBounds(50, 400, 150, 96);
		playAgainButton.setBorderPainted(false);
		playAgainButton.setContentAreaFilled(false);
		playAgainButton.setFocusable(false);
		playAgainButton.addActionListener(this);
		// add Home Button
		homeButton = new JButton(new ImageIcon("data/Home.png"));
		homeButton.setBounds(240, 400, 151, 96);
		homeButton.setBorderPainted(false);
		homeButton.setFocusable(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusable(false);
		homeButton.addActionListener(this);
		// add Exit Button
		exitButton = new JButton(new ImageIcon("data/exit.png"));
		exitButton.setBounds(Init.MAX_WIDTH_FRAME - 52, 10, 32, 32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
		this.add(exitButton);
		// add starRoomButton hack
		starRoomButton = new JButton(new ImageIcon("data/StarRoom.png"));
		starRoomButton.setBounds(20, 10, 42, 42);
		starRoomButton.setBorderPainted(false);
		starRoomButton.setContentAreaFilled(false);
		starRoomButton.setFocusable(false);
		starRoomButton.addActionListener(this);
		this.add(starRoomButton);
		// restartButton
		restartButton = new JButton(new ImageIcon("data/restart.png"));
		restartButton.setBounds(Init.MAX_WIDTH_FRAME - 52, 70, 32, 32);
		restartButton.setBorderPainted(false);
		restartButton.setContentAreaFilled(false);
		restartButton.setFocusable(false);
		restartButton.addActionListener(this);
	}

	void updateGame() {
		gameWorld.update();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g,randomIndexBG);
		gameWorld.draw((Graphics2D) g);
		if (gameWorld.isGameOver()) {
			this.add(playAgainButton);
			this.add(homeButton);
		}
	}
	public void drawBackground(Graphics g,int index)
	{
		if(isChangeBG==true)
		{
			randomIndexBG = randomBackground();
			try {
				for (int i = 3; i >= 0; --i)
				{
					backgroundBrownImages.remove(i);
				}
				backgroundBrownImages = new ArrayList<BufferedImage>();
				for (int i = 0; i < 4; ++i) {
					backgroundBrownImages.add(
						ImageIO.read(new File("data/Background/BackgroundStarRoom-" + randomIndexBG + ".png")));
				}
				repaint();
			} catch (IOException ex) {
			System.out.println("error data Image in GamePanel");
			}
			isChangeBG=false;
		}
		if (Init.isHackMode == false) {
			g.drawImage(backgroundImage, 0, 0, null);
		} else {
			backgroundPosY += backgroundSpeedY;
			for (int i = 0; i < 4; ++i) {
				g.drawImage(backgroundBrownImages.get(i), 0,
						backgroundPosY + backgroundBrownImages.get(i).getHeight() * i, null);
			}
			if (backgroundPosY >= 0) {
				backgroundPosY = -320;
			}
			g.drawImage(landImage, 0, 504, null);
		}
	}

	public boolean gameOver() {
		return gameWorld.isGameOver();
	}

	public void startGame() {
		if (thread == null) {
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public void restartGame() {
		gameWorld.restartGame();
	}

	@Override
	public void run() {
		while (isRunning) {
			if (gameWorld.isGameOver() == true) {
				repaint();
			} else {
				if (isPaused == false) {
					updateGame();
					repaint();
				}
			}
			try {
				thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) { // call back method nhấn phím
		if (isPaused == false) {
			gameWorld.getInputManager().processKeyPressed(e.getKeyCode());
			if (InputManager.start == Init.READY_GAME) {
				remove();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { // thả phím
		if (isPaused == false) {
			gameWorld.getInputManager().processKeyReleased(e.getKeyCode());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playAgainButton) {
			restartGame();
			remove();
		}
		if (e.getSource() == homeButton) {
			this.setVisible(false);
			restartGame();
			GameStateMenu.isPlayed = false;
			remove();
		}
		if (e.getSource() == pauseButton) {
			if (isPaused == false && gameOver() == false) {
				pauseButton.setIcon(imageNext);
				repaint();
				isPaused = true;
			} else if (isPaused == true && gameOver() == false) {
				pauseButton.setIcon(imagePause);
				repaint();
				isPaused = false;
			}
		}
		if (e.getSource() == starRoomButton) {
			if (Init.isHackMode == false && InputManager.start == Init.READY_GAME) {
				starRoomButton.setIcon(new ImageIcon("data/ChallengeRoom.png"));
				Init.isHackMode = true;
				this.add(restartButton);
			} else if (Init.isHackMode == true && InputManager.start == Init.READY_GAME) {
				starRoomButton.setIcon(new ImageIcon("data/StarRoom.png"));
				Init.isHackMode = false;
				this.remove(restartButton);
			}
		}
		if (e.getSource() == restartButton) {
			isChangeBG=true;
			this.restartGame();
		}
		if (e.getSource() == exitButton) {
			System.exit(0);
		}
	}

	public int randomBackground() {
		Random rand = new Random();
		int index = rand.nextInt(6) + 1;
		return index;
	}

	public void remove() {
		this.remove(playAgainButton);
		this.remove(homeButton);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gameWorld.getInputManager().processMouseAction(e.MOUSE_CLICKED);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
