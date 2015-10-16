package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;
import com.mygdx.srHelpers.InputHandler;
import com.mygdx.srHelpers.UpperHandler;

public class GameScreen implements Screen {

	private GameRenderer renderer;
	private float runTime = 0;
	private GameWorld world;

	public GameScreen() {

		float screenWidth = (float) Gdx.graphics.getWidth();
		float screenHeight = (float) Gdx.graphics.getHeight();
		float buttonWidth = screenWidth / 9;
		float rockWidth = screenWidth / 7;

		world = new GameWorld(buttonWidth, screenWidth, screenHeight, rockWidth);
		renderer = new GameRenderer(world, screenWidth, screenHeight);

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new InputHandler(world));
		multiplexer.addProcessor(new UpperHandler(world));
		
		Gdx.input.setInputProcessor(multiplexer);

	}

	@Override
	public void render(float delta) {
		
		world.update(delta);  //updates the game logic
		renderer.render();	  // then renders the graphics
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");

	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");

	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");

	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");

	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
