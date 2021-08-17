package objects;

public class Init 
{
	//Game Size
	public static final int MAX_WIDTH_FRAME = 438;
	public static final int MAX_HEIGHT_FRAME = 730;
	//State Game
	public static final int READY_GAME=0;
	public static final int PLAY_GAME=1;
	public static final int RESTART_GAME=2;
	//Ingame
	public static final int POSITION_LAND = 540;
	public static final int POSITION_CENTER = 175;
	//Dino State
	public static final int DIR_LEFT = 0;
	public static final int DIR_RIGHT = 1;
	public static final int STATE_IDLE = 0;
	public static final int STATE_RUN = 1;
	public static final int STATE_FALL = 2;
	public static final int WALL_LEFT = 0;
	public static final int WALL_RIGHT = 365;
	public static final int BLUE_DINO=0;
	public static final int GREEN_DINO=1;
	//BombRain
	public static final int DISTANCE_SUPERBOMBRAIN = 530;
	//Hack Mode
	public static boolean isHackMode=false;
}
