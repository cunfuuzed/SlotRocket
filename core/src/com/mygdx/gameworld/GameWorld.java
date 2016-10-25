package com.mygdx.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.gameobjects.Asteroid;
import com.mygdx.gameobjects.Battery;
import com.mygdx.gameobjects.Block;
import com.mygdx.gameobjects.Generator;
import com.mygdx.gameobjects.Ground;
import com.mygdx.gameobjects.PauseButton;
import com.mygdx.screens.GameScreen;
import com.mygdx.slotrocket.SRGame;

public class GameWorld {

	private Block launcher;
	private float buttonWidth;
	private float screenWidth;
	private float screenHeight;
	private float rockWidth;
	private Generator generator;
	private Ground ground;
	private float gapWidth;
	private Battery battery;
	private PauseButton pauseButton;
	private GameScreen screen;



	//passes in device dependent variables and creates game objects
	public GameWorld(float buttonWidth, float screenWidth, float screenHeight,
			float rockWidth, GameScreen screen) {
		this.screen = screen;

		this.buttonWidth = buttonWidth;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.rockWidth = rockWidth;
		this.launcher = new Block(screenWidth / 2 - (2 * buttonWidth),
				screenHeight - 4 * buttonWidth - buttonWidth / 2, buttonWidth,
				4, 4);
		this.ground = new Ground(0, launcher.getCoordY() - buttonWidth / 2,
				screenWidth, buttonWidth / 4);
		this.generator = new Generator(this);
		this.gapWidth = rockWidth/5;
		this.battery = new Battery(this);
		this.pauseButton = new PauseButton(new Rectangle(screenWidth - buttonWidth - 10, getGroundY() + 10, buttonWidth, buttonWidth),
				screenWidth - buttonWidth, getGroundY() + buttonWidth,screen);
		

	}

	public Battery getBattery() {
		return battery;
	}

	public float getButtonWidth() {
		return buttonWidth;
	}

	public Block getLauncher() {
		return launcher;
	}

	public Generator getGenerator() {
		return this.generator;
	}

	public void update(float delta) {
		this.battery.update(delta);
		this.generator.update(delta);
	}

	public float getRockWidth() {
		return rockWidth;
	}

	public float getScreenWidth() {
		return screenWidth;
	}
	
	public Ground getGround(){
		return ground;
	}
	
	public float getGroundY(){
		return ground.getBounds().y;
	}
	
	public float getGapWidth(){
		return gapWidth;
	}

	public PauseButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(PauseButton pauseButton) {
		this.pauseButton = pauseButton;
	}
}
