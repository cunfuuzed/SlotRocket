package com.mygdx.slotrocket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gameworld.ScoreKeeper;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;
import com.mygdx.srHelpers.AssetLoader;


public class SRGame extends Game {

	
	GameScreen game;
	MenuScreen menu;
	ScoreKeeper scoreKeeper;


	public SRGame() {

	}
	@Override
	public void create() {
		scoreKeeper = new ScoreKeeper(this);
		game = new GameScreen(this, menu);
		// menu screen intialized from PauseButton in GameWorld
//		menu = new MenuScreen(this, game);
		scoreKeeper.initWorld();


		Gdx.app.log("Slot Rocket" , "created");
		this.setScreen(menu);

	}
	
	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

	public GameScreen getGame() {
		return game;
	}

	public void setGame(GameScreen game) {
		this.game = game;
	}

	public MenuScreen getMenu() {

		return menu;


	}

	public void setMenu(MenuScreen menu) {
		this.menu = menu;
	}

	public void initMenu(){
		this.menu = new MenuScreen(this, this.game);
	}

	public ScoreKeeper getScoreKeeper() {
		return scoreKeeper;
	}
}
