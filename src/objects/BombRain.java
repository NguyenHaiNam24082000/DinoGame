package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import effect.ReadFileFromData;
import userinterface.GamePanel;
import userinterface.InputManager;

public class BombRain {
	private float posX;
	private float posY;
	private float mass = 4;
	private static int indexBombRain = 0;
	private List<ReadFileFromData> readData;
	private Rectangle rect;
	private int widthFrame;
	private int heightFrame;

	public BombRain() {
		super();
	}

	public BombRain(float posX, float posY) {
		readData = new ArrayList<ReadFileFromData>();
		readData.add(new ReadFileFromData("data/DataLoader/bomb.txt"));
		readData.add(new ReadFileFromData("data/DataLoader/bomb-2.txt"));
		setPosX(posX);
		setPosY(posY);
	}

	public void update() {
		readData.get(indexBombRain).getAnimation().update();
		widthFrame = readData.get(indexBombRain).getAnimation().getFrame().getWidth();
		heightFrame = readData.get(indexBombRain).getAnimation().getFrame().getHeight();
		if (InputManager.start == Init.READY_GAME) {
			if (Init.isHackMode == true) {
				indexBombRain = 1;
			} else
				indexBombRain = 0;
		}
		if (InputManager.start == Init.PLAY_GAME) {
			setPosY(getPosY() + mass);
		}
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(readData.get(indexBombRain).getAnimation().getFrame(), (int) getPosX(), (int) getPosY(), null);
	}

	public Rectangle getBound() {
		rect = new Rectangle();
		rect.x = (int) getPosX();
		rect.y = (int) getPosY();
		rect.width = widthFrame;
		rect.height = heightFrame;
		return rect;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public int getHeightFrame() {
		return heightFrame;
	}

	public int getWidthFrame() {
		return widthFrame;
	}

	public BufferedImage getFrame() {
		return readData.get(indexBombRain).getAnimation().getFrame();
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
}
