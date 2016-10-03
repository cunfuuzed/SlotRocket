package com.mygdx.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.screens.GameScreen;
import com.mygdx.srHelpers.AbstractButton;
/**
 * Pauses game as of 9/5/2016. may also bring up main menu/ in game menu when paused
 */
public class PauseButton extends AbstractButton{

    boolean paused;
    GameScreen screen;
    Vector2 position;
    Rectangle bounds;



    public PauseButton(Rectangle bounds, float x, float y, GameScreen screen) {
        this.bounds = bounds;
        position = new Vector2();
        position.x = x;
        position.y = y;
        this.screen = screen;
        paused = false;
    }




    @Override
    public boolean onPress() {

        if(paused){
            screen.setCurrentState(GameScreen.GameState.RUNNING);
            Gdx.app.log("PauseButton", " screen.setCurrentState(GameScreen.GameState.RUNNING)");
            paused = false;
        }else{
            screen.setCurrentState(GameScreen.GameState.PAUSED);
            Gdx.app.log("PauseButton", " screen.setCurrentState(GameScreen.GameState.PAUSED)");
            paused = true;
        }


        return false;
    }

    @Override
    public Rectangle getBounds() {
        return this.bounds;
    }


}
