package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import effect.ReadFileFromData;
import userinterface.GamePanel;
import userinterface.InputManager;

public class GreenDino extends DinoCharacter {
	public GreenDino(float posX, float posY) {
		super(posX, posY);
		getReadData().add(new ReadFileFromData("data/DataLoader/idleGreenDino.txt"));
		getReadData().add(new ReadFileFromData("data/DataLoader/moveGreenDino.txt"));
		getReadData().add(new ReadFileFromData("data/DataLoader/hitGreenDino.txt"));
	}

	@Override
	public void update() {
		getReadData().get(indexStateDino).getAnimation().update();
		setWidthFrame(getReadData().get(indexStateDino).getAnimation().getFrame().getWidth());
		setHeightFrame(getReadData().get(indexStateDino).getAnimation().getFrame().getHeight());
		setPosX(getPosX() + getSpeedX());
//		setPosY(Init.POSITION_LAND);
		if(getDirection() == 3)
		{
			this.setSpeedX(0);
		}
		if (getPosX() >= Init.WALL_RIGHT || getPosX() <= Init.WALL_LEFT) {
			indexStateDino = 2;
		}
		if(Init.isHackMode==true)
		{
			if(getPosX()>=Init.WALL_RIGHT)
			{
				this.setSpeedX(0);
				this.setPosX(Init.WALL_RIGHT-1);
			}
			if(getPosX()<=Init.WALL_LEFT)
			{
				this.setSpeedX(0);
				this.setPosX(Init.WALL_LEFT+1);
			}
		}
		if (this.getPosX() < Init.WALL_RIGHT && this.getPosX() > Init.WALL_LEFT && getDirection() != 3) {
			indexStateDino = 1;
			if (this.getDirection() == Init.DIR_LEFT) {
				this.setSpeedX(-16);
			} else if (this.getDirection() == Init.DIR_RIGHT) {
				this.setSpeedX(16);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (super.getDirection() == Init.DIR_LEFT) {
			if (GameWorld.isGameOver == true) {
				g2.drawImage(getReadData().get(Init.STATE_FALL).getAnimation().getFrame(),
						(int) (getPosX() + getWidthFrame()), (int) getPosY(), -getWidthFrame(), getHeightFrame(), null);
			} else
				g2.drawImage(getReadData().get(indexStateDino).getAnimation().getFrame(),
						(int) (getPosX() + getWidthFrame()), (int) getPosY(), -getWidthFrame(), getHeightFrame(), null);
		} else {
			if (GameWorld.isGameOver == true) {
				g2.drawImage(getReadData().get(Init.STATE_FALL).getAnimation().getFrame(), (int) getPosX(),
						(int) getPosY(), null);
			} else
				g2.drawImage(getReadData().get(indexStateDino).getAnimation().getFrame(), (int) getPosX(),
						(int) getPosY(), null);
		}

	}

	@Override
	public Rectangle getBound() {
		Rectangle rect = new Rectangle();
		rect.x = (int) this.getPosX();
		rect.y = (int) this.getPosY();
		rect.width = getWidthFrame();
		rect.height = getHeightFrame();
		return rect;
	}
}
