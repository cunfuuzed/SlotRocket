package com.mygdx.slotrocket;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.gameworld.ScoreKeeper;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.LoadScreen;
import com.mygdx.screens.MenuScreen;
import com.mygdx.srHelpers.AssetLoader;


public class SRGame extends Game {


    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private ScoreKeeper scoreKeeper;
    private LoadScreen loadScreen;
    private AssetManager manager;


    public SRGame() {

    }

    @Override
    public void create() {

        manager = new AssetManager();
        scoreKeeper = new ScoreKeeper(this);
        loadScreen = new LoadScreen(this);
        gameScreen = new GameScreen(this, menuScreen);
        // menuScreen screen intialized from PauseButton in GameWorld
//		menuScreen = new MenuScreen(this, gameScreen);
        scoreKeeper.initWorld();
        this.setScreen(loadScreen);


        Gdx.app.log("Slot Rocket", "created");


    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
        manager.clear();
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen() {
        this.setScreen(gameScreen);
    }

    public MenuScreen getMenuScreen() {

        return menuScreen;


    }

    public void setMenuScreen() {
        this.setScreen(menuScreen);
    }

    public AssetManager getManager() {
        return manager;
    }

    public LoadScreen getLoadScreen() {
        return loadScreen;
    }

    public void setLoadScreen() {
        this.setScreen(loadScreen);
    }

    public void initMenu() {
        this.menuScreen = new MenuScreen(this, this.gameScreen);
    }

    public ScoreKeeper getScoreKeeper() {
        return scoreKeeper;
    }
}
