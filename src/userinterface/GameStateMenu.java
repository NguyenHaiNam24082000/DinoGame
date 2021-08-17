package userinterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import objects.Init;

public class GameStateMenu extends JPanel implements ActionListener {
	private BufferedImage backgroundImage;
	private BufferedImage dinoDisplay;
	private JButton playButton;
	private JButton helpButton;
	private JButton exitButton;
	public static boolean isPlayed = false;
	private int logoPosY = 200;
	private boolean isPlaced = false;
	private Thread thread;

	public GameStateMenu() {
		try {
			backgroundImage = ImageIO.read(new File("data/Background(432x768).png"));
			dinoDisplay = ImageIO.read(new File("data/dinoCharacters-display.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		playButton = new JButton(new ImageIcon("data/Play.png"));
		helpButton = new JButton(new ImageIcon("data/question.png"));
		exitButton = new JButton(new ImageIcon("data/exit.png"));
		if (thread == null) {
			thread = new Thread();
		}
		this.addButton();
		this.setLayout(null);
	}

	public void addButton() {
		playButton.setBounds(50, 400, 150, 96);
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setFocusable(false);
		playButton.addActionListener(this);
		helpButton.setBounds(240, 400, 151, 96);
		helpButton.setBorderPainted(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setFocusable(false);
		helpButton.addActionListener(this);
		exitButton.setBounds(Init.MAX_WIDTH_FRAME-52, 10, 32, 32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
		this.add(playButton);
		this.add(helpButton);
		this.add(exitButton);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(dinoDisplay, (int) (Init.MAX_WIDTH_FRAME - dinoDisplay.getWidth()) / 2, logoPosY, this);
		if (logoPosY <= 220 && isPlaced == false) {
			logoPosY += 2;
			try {
				thread.sleep(75);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (logoPosY == 220) {
				isPlaced = true;
			}
			this.repaint();
		} else if (logoPosY >= 200 && isPlaced == true) {
			logoPosY -= 2;
			try {
				thread.sleep(75);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (logoPosY == 200) {
				isPlaced = false;
			}
			this.repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			isPlayed = true;
			GameFrame.cardLayout.next(GameFrame.container);
		}
		if (e.getSource() == helpButton) {
			JOptionPane optionPane = new JOptionPane();
			optionPane.showMessageDialog(this, "Press Space To Move and Press Number To Change dino Characters");
		}
		if (e.getSource() == exitButton)
		{
			System.exit(0);
		}
	}
}