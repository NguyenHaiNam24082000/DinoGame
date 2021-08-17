package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import effect.ReadFileFromData;
import effect.SoundPlay;
import userinterface.GameFrame;
import userinterface.GameStateMenu;
import userinterface.InputManager;

public class GameWorld {
	private List<DinoCharacter> listDinoCharacter;
	private InputManager inputManager;
	private Score score;
	private static int index;
	private SuperBombRain superBombRain;
	private GameStateMenu gameStateMenu;
	private BufferedImage imageBoardScore;
	private BufferedImage imageGameOver;
	private BufferedImage imageTapSpace;
	private BufferedImage imageGetReady;
	private ReadFileFromData readData;
	private int oldBestScore;
	private int bestScore;
	private int units;
	private int dozens;
	private int hundreds;
	private int imageBoardGameOverY = 300;
	private SoundPlay gameoverSound;
	public static boolean isGameOver = false;
	
	public GameWorld() {
		super();
		index = 0;
		gameStateMenu = new GameStateMenu();
		superBombRain = new SuperBombRain();
		score = new Score();
		inputManager = new InputManager(this);
		listDinoCharacter = new ArrayList<DinoCharacter>();
		listDinoCharacter.add(new BlueDino(Init.POSITION_CENTER, Init.POSITION_LAND));
		listDinoCharacter.add(new GreenDino(Init.POSITION_CENTER, Init.POSITION_LAND));
		try {
			imageBoardScore = ImageIO.read(new File("data/BoardScore.png"));
			imageGameOver = ImageIO.read(new File("data/GameOver(205x47).png"));
			imageTapSpace = ImageIO.read(new File("data/TapSpace.png"));
			imageGetReady = ImageIO.read(new File("data/GetReady(197x47).png"));
			gameoverSound=new SoundPlay(new File("data/Sound/GameOverSound.wav"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isGameOver=false;
//		this.setGameOver(false);
		readData = new ReadFileFromData();
		oldBestScore = readData.readBestScore("data\\DataLoader\\BestScoreDino.txt");
		bestScore = oldBestScore;
		units = bestScore % 10;
		dozens = (bestScore % 100 - units) / 10;
		hundreds = ((bestScore % 1000 - dozens * 10 - units)) / 100;
	}

	public void update() {
		this.chooseDino();
		listDinoCharacter.get(index).update();
		superBombRain.update();
		if (Score.point < oldBestScore) {
			bestScore = oldBestScore;
		} else {
			bestScore = Score.point;
			units = bestScore % 10;
			dozens = (bestScore % 100 - units) / 10;
			hundreds = ((bestScore % 1000 - dozens * 10 - units)) / 100;
		}
	}

	public void restartGame() {
		gameStateMenu = new GameStateMenu();
		GameStateMenu.isPlayed = true;
		superBombRain = new SuperBombRain();
		score = new Score();
		inputManager = new InputManager(this);
//		listDinoCharacter = new ArrayList<DinoCharacter>();
//		listDinoCharacter.add(new BlueDino(Init.POSITION_CENTER, Init.POSITION_LAND));
//		listDinoCharacter.add(new GreenDino(Init.POSITION_CENTER, Init.POSITION_LAND));
		listDinoCharacter.get(Init.BLUE_DINO).setPosX(Init.POSITION_CENTER);
		listDinoCharacter.get(Init.BLUE_DINO).setPosY(Init.POSITION_LAND);
		listDinoCharacter.get(Init.BLUE_DINO).setDirection(3);
		listDinoCharacter.get(Init.GREEN_DINO).setPosX(Init.POSITION_CENTER);
		listDinoCharacter.get(Init.GREEN_DINO).setPosY(Init.POSITION_LAND);
		listDinoCharacter.get(Init.GREEN_DINO).setDirection(3);
		DinoCharacter.indexStateDino=Init.STATE_IDLE;
		this.setGameOver(false);
		readData = new ReadFileFromData();
		oldBestScore = readData.readBestScore("data\\DataLoader\\BestScoreDino.txt");
		units = oldBestScore % 10;
		dozens = (oldBestScore % 100 - units) / 10;
		hundreds = ((oldBestScore % 1000 - dozens * 10 - units)) / 100;
	}

	public BufferedImage getImageNumber(int num) throws IOException {
		return ImageIO.read(new File("data/Number/Number-" + num + ".png"));
	}

	public void draw(Graphics2D g2) {
		listDinoCharacter.get(index).draw(g2);
		superBombRain.draw(g2);
		if (InputManager.start == Init.READY_GAME) {
			imageBoardGameOverY = 300;
			g2.drawImage(imageGetReady, (int) (Init.MAX_WIDTH_FRAME - imageGetReady.getWidth()) / 2, 125, null);
			g2.drawImage(imageTapSpace, (int) (Init.MAX_WIDTH_FRAME - imageTapSpace.getWidth()) / 2,
					Init.MAX_HEIGHT_FRAME - 90, null);
//			g2.drawImage(imageBoardScore, (int) ((Init.MAX_WIDTH_FRAME - imageBoardScore.getWidth()) / 2),
//					imageBoardGameOverY + 50, null);
//			g2.drawImage(imageGameOver, (int) (Init.MAX_WIDTH_FRAME - imageGameOver.getWidth()) / 2,
//					imageBoardGameOverY + 30, null);
		}
		if (isGameOver) {
			String str = "" + bestScore;
			readData.writeBestScore("data\\DataLoader\\BestScoreDino.txt", str);
			oldBestScore = readData.readBestScore("data\\DataLoader\\BestScoreDino.txt");
			g2.setFont(GameFrame.pixelFont);
			g2.drawImage(imageBoardScore, (int) ((Init.MAX_WIDTH_FRAME - imageBoardScore.getWidth()) / 2),
					imageBoardGameOverY + 50, null);
			g2.drawImage(imageGameOver, (int) (Init.MAX_WIDTH_FRAME - imageGameOver.getWidth()) / 2,
					imageBoardGameOverY + 30, null);
			g2.drawString("BEST", 193, imageBoardGameOverY + 110);
			g2.drawString("SCORE", 186, imageBoardGameOverY + 180);
			try {
				g2.drawImage(this.getImageNumber(hundreds), (int) (Init.MAX_WIDTH_FRAME - 81) / 2,
						imageBoardGameOverY + 115, null);
				g2.drawImage(this.getImageNumber(dozens), (int) (Init.MAX_WIDTH_FRAME - 81) / 2 + 29,
						imageBoardGameOverY + 115, null);
				g2.drawImage(this.getImageNumber(units), (int) (Init.MAX_WIDTH_FRAME - 81) / 2 + 59,
						imageBoardGameOverY + 115, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				g2.drawImage(score.getImageHundreds(), (int) (Init.MAX_WIDTH_FRAME - 81) / 2, imageBoardGameOverY + 190,
						null);
				g2.drawImage(score.getImageDozens(), (int) (Init.MAX_WIDTH_FRAME - 81) / 2 + 29, imageBoardGameOverY + 190,
						null);
				g2.drawImage(score.getImageUnits(), (int) (Init.MAX_WIDTH_FRAME - 81) / 2 + 59, imageBoardGameOverY + 190,
						null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (imageBoardGameOverY > 100) {
				imageBoardGameOverY -= 50;
			}
		}
	}

	public List<DinoCharacter> getListDinoCharacter() {
		return listDinoCharacter;
	}

	public void setListDinoCharacter(List<DinoCharacter> listDinoCharacter) {
		this.listDinoCharacter = listDinoCharacter;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public void setInputManager(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	public GameStateMenu getGameStateMenu() {
		return gameStateMenu;
	}

	public void setGameStateMenu(GameStateMenu gsm) {
		this.gameStateMenu = gsm;
	}

	public boolean isGameOver() {
		if (!isGameOver) {
			if (getListDinoCharacter().get(index).getPosX() >= Init.WALL_RIGHT
					|| getListDinoCharacter().get(index).getPosX() <= Init.WALL_LEFT) {
				if(Init.isHackMode==false)
				{
					gameoverSound.play();
					isGameOver = true;
					InputManager.start = Init.RESTART_GAME;
				}
				return isGameOver;
			}
			for (int i = 0; i < 8; ++i) {
				if (getListDinoCharacter().get(index).getBound().intersects(superBombRain.getBound()[i]) == true) {
					if(Init.isHackMode==false)
					{
						isGameOver = true;
						gameoverSound.play();
						InputManager.start = Init.RESTART_GAME;
					}
					SuperBombRain.isGameOver[i]=true;
					return isGameOver;
				}
			}
		}
		return isGameOver;
	}

	public void setGameOver(boolean isOver) {
		isGameOver = isOver;
	}

	public void chooseDino() {
		switch (InputManager.indexDino) {
		case Init.BLUE_DINO:
			index = Init.BLUE_DINO;
			break;
		case Init.GREEN_DINO:
			index = Init.GREEN_DINO;
			break;
		}
	}

}
