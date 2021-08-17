package userinterface;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import effect.SoundPlay;
import objects.DinoCharacter;
import objects.GameWorld;
import objects.Init;

public class InputManager {

	private GameWorld gameWorld;
	private static int direction = 0;
	private static int index = 0;
	public static int start = 0;
	public static int indexDino = 0;
	private SoundPlay sound;

	public InputManager(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		start = 0;
		direction = 0;
		index = 0;
		sound = new SoundPlay(new File("data/Sound/TapSound.wav"));
	}

	public void processKeyPressed(int KeyCode) {
		if (gameWorld.getGameStateMenu().isPlayed == true) {
			if (KeyCode == KeyEvent.VK_SPACE) {
				if (start == Init.RESTART_GAME) {
//					gameWorld.restartGame();
				} else if (start == Init.READY_GAME || start == Init.PLAY_GAME) {
					move();
				}
			}
			if (start == Init.READY_GAME) {
				if (KeyCode == KeyEvent.VK_1) {
					indexDino = 0;
				}
				if (KeyCode == KeyEvent.VK_2) {
					indexDino = 1;
				}
			}
		}
	}

	public void checkDirection() {
		if (direction >= 2) {
			direction = 0;
		}
		if (direction == 1) {
			gameWorld.getListDinoCharacter().get(indexDino).setDirection(Init.DIR_RIGHT);
		} else if (direction == 0) {
			gameWorld.getListDinoCharacter().get(indexDino).setDirection(Init.DIR_LEFT);
		}
	}

	public void processKeyReleased(int KeyCode) {
	}

	public void processMouseAction(int click) {
//		if (gameWorld.getGameStateMenu().isPlayed == true) {
//			if (click == MouseEvent.MOUSE_CLICKED) {
//				if (start == 2) {
//					gameWorld.restartGame();
//				} else if (start == 0 || start == 1) {
//					move();
//				}
//			}
//		}
	}
	
	public void move() {
		sound.play();
		direction++;
		start = 1;
		checkDirection();
	}
}
