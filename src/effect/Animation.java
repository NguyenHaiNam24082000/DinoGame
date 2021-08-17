package effect;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	private List<BufferedImage> frames;
	private int frameIndex=0;
	private long delayTime;
	private long previousTime;
	public Animation() {
		super();
	}
	public Animation(int delayTime)
	{
		this.delayTime=delayTime;
		frames= new ArrayList<BufferedImage>();
	}
	public void update()
	{
		if (System.currentTimeMillis()-previousTime>delayTime)
		{
			frameIndex++;
			if(frameIndex>=frames.size())
			{
				frameIndex=0;
			}
			previousTime=System.currentTimeMillis();
		}
	}
	public void addFrame(BufferedImage frame)
	{
		frames.add(frame);
	}
	public BufferedImage getFrame()
	{
		if(frames.size()>0)
		{
			return frames.get(frameIndex);
		}
		return null;
	}
	public long getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(long delayTime) {
		this.delayTime = delayTime;
	}
	public int getFrameIndex() {
		return frameIndex;
	}
	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}
	
}
