package com.mygdx.slotrocket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.GameScreen;
import com.mygdx.srHelpers.AssetLoader;


public class SRGame extends Game {
	
	GameScreen game;

	@Override
	public void create() {
		game = new GameScreen();
		Gdx.app.log("Slot Rocket" , "created");
		this.setScreen(game);

	}
	
	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}
