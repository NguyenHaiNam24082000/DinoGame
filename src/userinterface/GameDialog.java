package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objects.GameWorld;
import objects.Score;

public class GameDialog extends JDialog 
{
	private JPanel panel;
	private JLabel labelBestScore;
	private JLabel labelBestScorePoint;
	private JLabel labelScore;
	private JLabel labelScorePoint;
	private JLabel boardScore;
	private JButton playAgainButton;
	private JButton homeButton;
	private static final int WIDTH=252;
	private static final int HEIGHT=259;
	private GameWorld gameWorld;
	private Score score;
	public GameDialog() 
	{
		super();
		score=new Score();
		panel=new JPanel();
		panel.setLayout(null);
		Dimension dimension= new Dimension(this.getToolkit().getScreenSize());
		this.setBounds((int)(dimension.getWidth()-WIDTH)/2, (int)230, WIDTH, HEIGHT);
		this.setUndecorated(true);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		boardScore = new JLabel(new ImageIcon("data/BoardScore.png"));
		playAgainButton = new JButton(new ImageIcon("data/Play.png"));
		homeButton = new JButton(new ImageIcon("data/Home.png"));
		this.add(panel);
		this.update();
	}
	public void update()
	{
		labelBestScore = new JLabel("BEST");
		labelBestScore.setFont(GameFrame.pixelFont);
		labelBestScore.setBounds(100, 35, 150, 30);
		labelBestScore.setForeground(Color.white);
		panel.add(labelBestScore);
		labelScore = new JLabel("SCORE");
		labelScore.setFont(GameFrame.pixelFont);
		labelScore.setForeground(Color.white);
		labelScore.setBounds(92, 110, 100, 30);
		panel.add(labelScore);
		labelBestScorePoint= new JLabel(""+Score.point);
		labelBestScorePoint.setFont(GameFrame.pixelFont);
		labelBestScorePoint.setForeground(Color.white);
		labelBestScorePoint.setBounds(117, 135, 30, 30);
		panel.add(labelBestScorePoint);
		playAgainButton.setBounds(100,175,50,32);
		playAgainButton.setBorderPainted(false);
		playAgainButton.setFocusable(false);
		panel.add(playAgainButton);
		homeButton.setBounds(101,210,49,31);
		homeButton.setBorderPainted(false);
		homeButton.setFocusable(false);
		panel.add(homeButton);
		boardScore.setBounds(0, 0, WIDTH, HEIGHT);
		boardScore.setVisible(true);
		panel.add(boardScore);
	}
	public JButton getPlayAgainButton() {
		return playAgainButton;
	}
	public void setPlayAgainButton(JButton playAgainButton) {
		this.playAgainButton = playAgainButton;
	}
	public JButton getHomeButton() {
		return homeButton;
	}
	public void setHomeButton(JButton homeButton) {
		this.homeButton = homeButton;
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
