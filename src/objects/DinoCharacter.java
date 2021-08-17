package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import effect.ReadFileFromData;
import userinterface.GamePanel;

public abstract class DinoCharacter {
	private float posX;
	private float speedX;
	private float posY;
	private float speedY;
	private int direction = 3;
	private int widthFrame;
	private int heightFrame;
	private List<ReadFileFromData> readData;
	public static int indexStateDino = Init.STATE_IDLE;

	public DinoCharacter(float posX, float posY) {
		super();
		readData = new ArrayList<ReadFileFromData>();
		this.posX = posX;
		this.posY = posY;
		indexStateDino = Init.STATE_IDLE;
	}

	public abstract Rectangle getBound();

	public abstract void update();

	public abstract void draw(Graphics2D g2);

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public List<ReadFileFromData> getReadData() {
		return readData;
	}

	public void setReadData(List<ReadFileFromData> readData) {
		this.readData = readData;
	}

	public int getWidthFrame() {
		return widthFrame;
	}

	public void setWidthFrame(int widthFrame) {
		this.widthFrame = widthFrame;
	}

	public int getHeightFrame() {
		return heightFrame;
	}

	public void setHeightFrame(int heightFrame) {
		this.heightFrame = heightFrame;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

}
