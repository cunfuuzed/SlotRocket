package com.mygdx.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;
import com.mygdx.slotrocket.SRGame;
import com.mygdx.srHelpers.AbstractButton;
import com.mygdx.srHelpers.ScreenState;

/**
 * Pauses game as of 9/5/2016. may also bring up main menu/ in game menu when paused
 */
public class PauseButton extends AbstractButton{

    private boolean paused;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private SRGame myGame;
    private Vector2 position;
     private Rectangle bounds;




    public PauseButton(Rectangle bounds, float x, float y, GameScreen gameScreen) {
        this.bounds = bounds;
        position = new Vector2();
        position.x = x;
        position.y = y;
        this.gameScreen = gameScreen;
        myGame = gameScreen.getMyGame();
        myGame.initMenu();
        menuScreen = myGame.getMenuScreen();

        paused = false;
    }




    @Override
    public boolean onPress() {

        Gdx.app.log("PauseButton", " clicked");

        if(myGame.getScreen().equals(menuScreen)){
//            gameScreen.setCurrentState(GameScreen.GameState.RUNNING);
            myGame.setScreen(gameScreen);
            gameScreen.setState(ScreenState.RUNNING);
            Gdx.app.log("PauseButton", " gameScreen.setCurrentState(GameScreen.GameState.RUNNING)");
            paused = false;
        }else{
//            gameScreen.setCurrentState(GameScreen.GameState.PAUSED);
            gameScreen.setState(ScreenState.PAUSED);
            menuScreen.setState(ScreenState.RUNNING);
            myGame.setScreen(menuScreen);
            Gdx.app.log("PauseButton", " gameScreen.setCurrentState(GameScreen.GameState.PAUSED)");
            paused = true;
        }


        return false;
    }

    @Override
    public Rectangle getBounds() {
        return this.bounds;
    }


}
