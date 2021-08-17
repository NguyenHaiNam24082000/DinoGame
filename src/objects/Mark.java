package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import userinterface.GameFrame;

public class Mark {
	private int units;
	private int dozens;
	private int hundreds;
	private int point;
	public Mark()
	{
		super();
		units=0;
		dozens=0;
		hundreds=0;
	}
	public void update(int point)
	{
		this.point=point;
		units=getPoint()%10;
		dozens=(getPoint()%100-units)/10;
		hundreds=(getPoint()%1000-dozens*10-units)/100;
	}
	public void draw(Graphics2D g2)
	{
		try {
			g2.drawImage(getImageHundreds(),(int)(GameFrame.MAX_WIDTH_FRAME-81)/2, 200, null);
			g2.drawImage(getImageDozens(),(int)(GameFrame.MAX_WIDTH_FRAME-81)/2+29, 200, null);
			g2.drawImage(getImageUnits(),(int)(GameFrame.MAX_WIDTH_FRAME-81)/2+59, 200, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getImageUnits() throws IOException
	{
		return ImageIO.read(new File("data/Number/Number-"+units+".png"));
	}
	public BufferedImage getImageDozens() throws IOException
	{
		return ImageIO.read(new File("data/Number/Number-"+dozens+".png"));
	}
	public BufferedImage getImageHundreds() throws IOException
	{
		return ImageIO.read(new File("data/Number/Number-"+hundreds+".png"));
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getUnits()
	{
		return units;
	}
}
