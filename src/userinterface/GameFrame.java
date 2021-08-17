package userinterface;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import effect.SoundPlay;
import objects.Init;

import java.awt.Container;
import java.awt.Cursor;

public class GameFrame extends JFrame 
{

	private GamePanel gamePanel;
	private GameStateMenu gsm;
	private BufferedImage CursorImg;
	public static CardLayout cardLayout;
	public static Font pixelFont;
	public static Container container;

	public GameFrame() {
		super("dino");
		this.pack();
		try {
			CursorImg = ImageIO.read(new File("data/Cursor.png"));
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/04B_30__.TTF")).deriveFont(14f);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(CursorImg, new Point(0, 0), "Cursor Default");
		this.getContentPane().setCursor(cursor);
		container = getContentPane();
		gsm = new GameStateMenu();
		gamePanel = new GamePanel();
		cardLayout = new CardLayout();
		container.setLayout(cardLayout);
		this.setLocation();
		this.addGame();
		this.startGame();
		this.dispose();
		this.setUndecorated(true);
	}

	public void setLocation() {
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = new Dimension(toolkit.getScreenSize());
		this.setBounds((dimension.width - Init.MAX_WIDTH_FRAME-5) / 2, (dimension.height - Init.MAX_HEIGHT_FRAME) / 2,
				Init.MAX_WIDTH_FRAME-5, Init.MAX_HEIGHT_FRAME);
	}

	public void addGame() {
		container.add(gsm);
		container.add(gamePanel);
		this.addKeyListener(gamePanel);
		this.addMouseListener(gamePanel);
	}

	public void startGame() {
		gamePanel.startGame();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
		gameFrame.setVisible(true);
	}
}
