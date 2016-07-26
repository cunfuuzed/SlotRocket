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
		//gets device screen width
		float screenWidth = (float) Gdx.graphics.getWidth();
		//gets device screen height
		float screenHeight = (float) Gdx.graphics.getHeight();
		//sets the lower screen buttons to a standard fraction of the screen
		//so it's the same ratio on different size device screens
		float buttonWidth = screenWidth / 9;
		//sets the ratio of the size of the falling asteroids to the devices screen width
		//so it's scaled to different size device screens
		float rockWidth = screenWidth / 7;

		//creates a gameworld and render object, these are the two main objects that talk to
		//each other to run the game logic (game obj) and render  the graphics (render obj)
		world = new GameWorld(buttonWidth, screenWidth, screenHeight, rockWidth);
		renderer = new GameRenderer(world, screenWidth, screenHeight);

		//creates an input multiplexer, allowing for two different input handlers,
		//one for the upper screen where the asteroids are and one for controlling the blocks
		//on the bottom
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new InputHandler(world));
		multiplexer.addProcessor(new UpperHandler(world));
		
		Gdx.input.setInputProcessor(multiplexer);

	}
	
	//this is called in the main thread of the game, calls game world update and then renders
	@Override
	public void render(float delta) {
		
		world.update(delta);  //updates the game logic
		renderer.render();	  // then renders the graphics
	}

	//following methods print to the console changes to the gamescreen, incuding
	//resizing, opening and closing
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
