package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import effect.ReadFileFromData;
import effect.SoundPlay;
import userinterface.GamePanel;

public class SuperBombRain {
	private List<BombRain> bombRains;
	private ReadFileFromData readData;
	private BombRain bomb;
	private Rectangle rect[];
	private Score score;
	private int point = 0;
	private int deltaX;
	private int deltaY;
	private SoundPlay soundExplotion;
	private SoundPlay soundScore;
	private boolean isExplotionSoundPlay=false;
	private boolean[] isPlus;
	public static boolean[] isGameOver;

	public SuperBombRain() {
		super();
		soundExplotion = new SoundPlay(new File("data/Sound/ExplotionSound.wav"));
		soundScore = new SoundPlay(new File("data/Sound/ScoreSound.wav"));
		readData = new ReadFileFromData("data/DataLoader/explotion.txt");
		score = new Score();
		bombRains = new ArrayList<BombRain>();
		isPlus=new boolean[8];
		isGameOver=new boolean[8];
		for (int i = 0; i < 4; ++i) {
			int rand = getRandomX();
			bomb = new BombRain(-rand, -50 - i * 300);
			bombRains.add(bomb);
			isPlus[i]=false;
			isGameOver[i]=false;
			bomb = new BombRain(-rand + Init.DISTANCE_SUPERBOMBRAIN, -50 - i * 300);
			bombRains.add(bomb);
			isPlus[i+1]=false;
			isGameOver[i]=false;
		}
	}

	public int getRandomX() {
		Random rand = new Random();
		int randPosX;
		randPosX = rand.nextInt(201) + 50;
		return randPosX;
	}

	public void update() {
		readData.getAnimation().update();
		for (int i = 0; i < 8; ++i) {
			bombRains.get(i).update();
			if ((int) bombRains.get(i).getPosY() >= Init.POSITION_LAND && i % 2 == 0 && isPlus[i]==false) {
				soundScore.play();
				point++;
				isPlus[i]=true;
				score.update(point);
			}
		}
		for (int i = 0; i < 8; ++i) {
			if (bombRains.get(i).getPosY() >= 566) {
				int rand = getRandomX();
				bombRains.get(i).setPosY(bombRains.get(i).getPosY() - 1200);
				bombRains.get(i + 1).setPosY(bombRains.get(i + 1).getPosY() - 1200);
				bombRains.get(i).setPosX(-rand);
				bombRains.get(i + 1).setPosX(-rand + Init.DISTANCE_SUPERBOMBRAIN);
				this.setDeltaX((readData.getAnimation().getFrame().getWidth() - bombRains.get(i).getWidthFrame()) / 2);
				this.setDeltaX(
						(readData.getAnimation().getFrame().getHeight() - bombRains.get(i).getHeightFrame()) / 2);
				isPlus[i]=false;
				isExplotionSoundPlay=false;
				isGameOver[i]=false;
				isGameOver[i+1]=false;
			}
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < 8; ++i) {
			g2.setColor(Color.black);
//			g2.drawRect(getBound()[i].x, getBound()[i].y, getBound()[i].width, getBound()[i].height);
			if (bombRains.get(i).getPosY() < Init.POSITION_LAND + 10 || isGameOver[i]==false) {
				bombRains.get(i).draw(g2);
			}
			if (bombRains.get(i).getPosY() >= Init.POSITION_LAND + 10|| isGameOver[i]==true) {
				if(isExplotionSoundPlay==false)
				{
					soundExplotion.play();
					isExplotionSoundPlay=true;
				}
				g2.drawImage(readData.getAnimation().getFrame(), (int) (bombRains.get(i).getPosX() - getDeltaX()),
						(int) (bombRains.get(i).getPosY() - getDeltaY()), null);
			}
		}
		score.draw(g2);
	}

	public Rectangle getBound()[] {
		rect = new Rectangle[8];
		for (int i = 0; i < 8; ++i) {
			if (bombRains.get(i).getPosY() < 560) {
				rect[i] = bombRains.get(i).getBound();
			}
			if (bombRains.get(i).getPosY() >= 560) {
				Rectangle setupRect = new Rectangle();
				setupRect.x = (int) (bombRains.get(i).getPosX() - getDeltaX());
				setupRect.y = (int) (bombRains.get(i).getPosY() - getDeltaY());
				setupRect.width = readData.getAnimation().getFrame().getWidth();
				setupRect.height = readData.getAnimation().getFrame().getHeight();
				rect[i] = setupRect;
			}
		}
		return rect;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	public List<BombRain> getBombRains() {
		return bombRains;
	}

	public void setBombRains(List<BombRain> bombRains) {
		this.bombRains = bombRains;
	}
}
