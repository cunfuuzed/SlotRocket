package com.mygdx.srHelpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gameobjects.Asteroid;
import com.mygdx.gameobjects.Battery;
import com.mygdx.gameobjects.Block;
import com.mygdx.gameobjects.Generator;
import com.mygdx.gameworld.GameWorld;

public class UpperHandler implements InputProcessor{
	
	private GameWorld world;
	private Block launcher;
	private Generator generator;
	private Array<Asteroid> asteroids;
	private Battery battery;
	
	
	public UpperHandler(GameWorld world){

		this.world = world;
		this.launcher = world.getLauncher();
		this.generator = world.getGenerator();
		this.asteroids = generator.getAsteroids();
		this.battery = world.getBattery();
		
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
		for(int i = 0; i < asteroids.size; i++){
			Asteroid item = (Asteroid) asteroids.get(i);
			if(item.getBounds().contains(screenX, screenY)){
				battery.fire(i);
				launcher.resetCells();
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
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

}
