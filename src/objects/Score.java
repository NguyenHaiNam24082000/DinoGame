package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import userinterface.GameFrame;

public class Score {
	public static int units;
	public static int dozens;
	public static int hundreds;
	public static int point;

	public Score() {
		super();
		units = 0;
		dozens = 0;
		hundreds = 0;
	}

	public void update(int scorePoint) {
		point = scorePoint;
		units = point % 10;
		dozens = (point % 100 - units) / 10;
		hundreds = (point % 1000 - dozens * 10 - units) / 100;
	}

	public void draw(Graphics2D g2) {
		try {
			g2.drawImage(getImageHundreds(), (int) (Init.MAX_WIDTH_FRAME - 81) / 2, 200, null);
			g2.drawImage(getImageDozens(), (int) (Init.MAX_WIDTH_FRAME - 81) / 2 + 29, 200, null);
			g2.drawImage(getImageUnits(), (int) (Init.MAX_WIDTH_FRAME - 81) / 2 + 59, 200, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImageUnits() throws IOException {
		return ImageIO.read(new File("data/Number/Number-" + units + ".png"));
	}

	public BufferedImage getImageDozens() throws IOException {
		return ImageIO.read(new File("data/Number/Number-" + dozens + ".png"));
	}

	public BufferedImage getImageHundreds() throws IOException {
		return ImageIO.read(new File("data/Number/Number-" + hundreds + ".png"));
	}
}
