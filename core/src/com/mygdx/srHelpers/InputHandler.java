package com.mygdx.srHelpers;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.gameobjects.Block;
import com.mygdx.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

	private GameWorld world;
	private Block launcher;
	private boolean fingerDown;
	private boolean clicked[][];

	public InputHandler(GameWorld world) {
		this.world = world;
		this.launcher = world.getLauncher();// reference to the
		// launcher object created in the gameworld object
		fingerDown = false;
		clicked = new boolean[launcher.getRows()][launcher.getColumns()];
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for (int i = 0; i < launcher.getRows(); i++) {
			for (int j = 0; j < launcher.getColumns(); j++) {
				if (launcher.getButtons()[i][j].contains(screenX, screenY)) {
					if (clicked[i][j] == false) {
						if (launcher.getCellVis(i, j) == false) {
							for(int p = j; p < launcher.getCells()[i].length; p++){
								launcher.setCellVis(i, p, true);
							}
							launcher.setCellVis(i, j, true);
						} else {
							for (int k = j; k > -1; k--) {
								launcher.setCellVis(i, k, false);
							}
						}
						clicked[i][j] = true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		resetClicked();
		fingerDown = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for (int i = 0; i < launcher.getRows(); i++) {
			for (int j = 0; j < launcher.getColumns(); j++) {
				if (launcher.getButtons()[i][j].contains(screenX, screenY)) {
					if (clicked[i][j] == false) {
						if (launcher.getCellVis(i, j) == false) {
							for(int p = j; p < launcher.getCells()[i].length; p++){
								launcher.setCellVis(i, p, true);
							}
							launcher.setCellVis(i, j, true);
						} else {
							for (int k = j; k > -1; k--) {
								launcher.setCellVis(i, k, false);
							}
						}
						clicked[i][j] = true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	private void resetClicked() {
		// not the most efficient possible method but will be fast
		// for a set complexity of a 4x4 matrix
		for (int i = 0; i < clicked.length; i++) {
			for (int j = 0; j < clicked[0].length; j++) {
				clicked[i][j] = false;
			}
		}
	}

}
